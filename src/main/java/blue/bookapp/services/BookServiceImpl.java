package blue.bookapp.services;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.commands.PagesCommand;
import blue.bookapp.converters.BookCommandToBook;
import blue.bookapp.converters.BookToBookCommand;
import blue.bookapp.converters.PagesToPagesCommand;
import blue.bookapp.domain.Book;
import blue.bookapp.domain.Pages;
import blue.bookapp.domain.Publisher;
import blue.bookapp.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private BookCommandToBook bookCommandToBook;
    private BookToBookCommand bookToBookCommand;
    private PagesToPagesCommand pagesToPagesCommand;
    private EntityManager entityManager;

    public BookServiceImpl(BookRepository bookRepository, BookCommandToBook bookCommandToBook, BookToBookCommand bookToBookCommand, PagesToPagesCommand pagesToPagesCommand, EntityManager entityManager) {
        this.bookRepository = bookRepository;
        this.bookCommandToBook = bookCommandToBook;
        this.bookToBookCommand = bookToBookCommand;
        this.pagesToPagesCommand = pagesToPagesCommand;
        this.entityManager = entityManager;
    }

    @Override
    public Set<BookCommand> listBooksByAuthorName(String author) {
        Set<BookCommand> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(book -> books.add(bookToBookCommand.convert(book)));
        Set<BookCommand> savedBooks = new HashSet<>();
        books.stream().forEach(book ->
        {
            if (book.getAuthor().getName() == author)
            {
                savedBooks.add(book);
            }
        });

        return savedBooks;
    }

    @Override
    public Set<BookCommand> listBooksByPublisher(Publisher publisher) {
        Set<BookCommand> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(book -> books.add(bookToBookCommand.convert(book)));
        Set<BookCommand> savedBooks = new HashSet<>();
        books.stream().forEach(book ->
        {
            if (book.getPublisher() == publisher)
            {
                savedBooks.add(book);
            }
        });
        return savedBooks;
    }

    @Override
    public Set<BookCommand> listBooksByYear(int year) {
        Set<BookCommand> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(book -> books.add(bookToBookCommand.convert(book)));
        Set<BookCommand> savedBooks = new HashSet<>();
        books.stream().forEach(book ->
        {
            if (book.getYear() == year)
            {
                savedBooks.add(book);
            }
        });
        return savedBooks;
    }

    @Override
    public void findByEAN(BigDecimal ean) {
        bookRepository.findAll().forEach(book -> book.getEAN().equals(ean));
        log.debug("Found EAN");
    }

    @Override
    public Set<BookCommand> listByTitle(String title) {
        Set<BookCommand> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(book -> books.add(bookToBookCommand.convert(book)));
        Set<BookCommand> savedBooks = new HashSet<>();
        books.stream().forEach(book ->
        {
            if (book.getTitle() == title)
            {
                savedBooks.add(book);
            }
        });
        return savedBooks;
    }

    @Override
    @Transactional
    public BookCommand addBook(BookCommand bookCommand) {
        Book book = bookCommandToBook.convert(bookCommand);
        if (bookCommand.getId() == null)
        {
            bookRepository.save(book);
        }
        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        if (!optionalBook.isPresent())
        {
            throw new RuntimeException("Book already exists");
        }
        log.debug("Saving new book.");
        bookRepository.save(book);

        return bookToBookCommand.convert(book);
    }

    @Override
    @Transactional
    public void removeBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (!bookOptional.isPresent())
            throw new RuntimeException("Book not found!");
        Book book = bookOptional.get();

        if (book.getAuthor() != null) {
            book.getAuthor().getBooks().remove(book);
            book.setAuthor(null);
        }

        if (book.getPublisher() != null) {
            book.getPublisher().getBooks().remove(book);
            book.setPublisher(null);
        }


        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BookCommand updateBook(BookCommand bookCommand) {

        Set<Pages> pages = bookRepository.findById(bookCommandToBook.convert(bookCommand).getId()).get().getPages();
        System.out.println("Pages size: " + pages);

        Set<PagesCommand> pagesCommands = new HashSet<>();
        pages.forEach(pages1 -> pagesCommands.add(pagesToPagesCommand.convert(pages1)));

        pagesCommands.forEach(pagesCommand -> pagesCommand.setBookId(bookCommand.getId()));

        bookCommand.setPages(pagesCommands);

        Book book = bookCommandToBook.convert(bookCommand);
        if (book.getImage() != null)
            book.setImage(bookCommand.getImage());
        bookRepository.save(book);
        return bookToBookCommand.convert(book);
    }

    @Override
    public Set<BookCommand> listBooks() {
        Set<BookCommand> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(book -> books.add(bookToBookCommand.convert(book)));
        return books;
    }

    @Override
    @Transactional
    public BookCommand findCommandById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (!bookOptional.isPresent())
        {
            throw new RuntimeException("Book not found!");
        }

        return bookToBookCommand.convert(bookOptional.get());
    }

    @Override
    @Transactional
    public BookCommand updateAuthorPublisher(BookCommand bookCommand) {

        Optional<Book> bookOptional = bookRepository.findById(bookCommand.getId());
        if (!bookOptional.isPresent())
            throw new RuntimeException("Book not found!");
        Book book = bookOptional.get();

        if (book.getAuthor() == null || book.getPublisher() == null) {
            if (book.getAuthor() == null)
            {
                book.setAuthor(bookCommand.getAuthor());
                book.getAuthor().getBooks().add(book);
            }
            if (book.getPublisher() == null) {
                book.setPublisher(bookCommand.getPublisher());
                book.getPublisher().getBooks().add(book);
            }
                bookRepository.save(book);
                return bookToBookCommand.convert(book);
        }

        book.getAuthor().getBooks().remove(book);
        book.setAuthorOnly(bookCommand.getAuthor());
        book.getPublisher().getBooks().remove(book);
        book.setPublisherOnly(bookCommand.getPublisher());

        entityManager.createNativeQuery(
                "INSERT INTO AUTHOR_BOOKS (author_id, books_id) VALUES (" + book.getAuthor().getId() + ","
                                            + book.getId()+")").executeUpdate();

        entityManager.createNativeQuery(
                "INSERT INTO PUBLISHER_BOOKS (publisher_id, books_id) VALUES (" + book.getPublisher().getId() + ","
                + book.getId()+")").executeUpdate();


        bookRepository.save(book);


        return bookToBookCommand.convert(book);
    }
}
