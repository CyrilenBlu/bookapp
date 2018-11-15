package blue.bookapp.services;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.converters.BookCommandToBook;
import blue.bookapp.converters.BookToBookCommand;
import blue.bookapp.converters.PublisherCommandToPublisher;
import blue.bookapp.domain.Book;
import blue.bookapp.domain.Publisher;
import blue.bookapp.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private PublisherCommandToPublisher publisherCommandToPublisher;

    public BookServiceImpl(BookRepository bookRepository, BookCommandToBook bookCommandToBook, BookToBookCommand bookToBookCommand, PublisherCommandToPublisher publisherCommandToPublisher) {
        this.bookRepository = bookRepository;
        this.bookCommandToBook = bookCommandToBook;
        this.bookToBookCommand = bookToBookCommand;
        this.publisherCommandToPublisher = publisherCommandToPublisher;
    }

    @Override
    public Set<Book> listBooksByAuthorName(String author) {
        Set<Book> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(books::add);
        Set<Book> savedBooks = new HashSet<>();
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
    public Set<Book> listBooksByPublisher(Publisher publisher) {
        Set<Book> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(books::add);
        Set<Book> savedBooks = new HashSet<>();
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
    public Set<Book> listBooksByYear(int year) {
        Set<Book> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(books::add);
        Set<Book> savedBooks = new HashSet<>();
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
    public Set<Book> listByTitle(String title) {
        Set<Book> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(books::add);
        Set<Book> savedBooks = new HashSet<>();
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
    public void addBook(BookCommand bookCommand) {
        Book book = bookCommandToBook.convert(bookCommand);
        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        if (!optionalBook.isPresent())
        {
            throw new RuntimeException("Book already exists");
        }
        log.debug("Saving new book.");
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void removeBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BookCommand updateBookById(BookCommand bookCommand) {
        Book detachedBook = bookCommandToBook.convert(bookCommand);
        Book savedBook = bookRepository.save(detachedBook);
        log.debug("Saved book id: " + savedBook.getId());
        return bookToBookCommand.convert(savedBook);
    }

    @Override
    public Set<Book> listBooks() {
        Set<Book> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(books::add);
        return books;
    }
}
