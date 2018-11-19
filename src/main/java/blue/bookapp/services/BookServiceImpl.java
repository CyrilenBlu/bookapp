package blue.bookapp.services;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.commands.PagesCommand;
import blue.bookapp.converters.*;
import blue.bookapp.domain.Book;
import blue.bookapp.domain.Pages;
import blue.bookapp.domain.Publisher;
import blue.bookapp.repositories.BookRepository;
import blue.bookapp.repositories.PagesRepository;
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
    private PagesRepository pagesRepository;
    private PagesCommandToPages pagesCommandToPages;
    private PagesToPagesCommand pagesToPagesCommand;

    public BookServiceImpl(BookRepository bookRepository, BookCommandToBook bookCommandToBook, BookToBookCommand bookToBookCommand, PublisherCommandToPublisher publisherCommandToPublisher, PagesRepository pagesRepository, PagesCommandToPages pagesCommandToPages, PagesToPagesCommand pagesToPagesCommand) {
        this.bookRepository = bookRepository;
        this.bookCommandToBook = bookCommandToBook;
        this.bookToBookCommand = bookToBookCommand;
        this.publisherCommandToPublisher = publisherCommandToPublisher;
        this.pagesRepository = pagesRepository;
        this.pagesCommandToPages = pagesCommandToPages;
        this.pagesToPagesCommand = pagesToPagesCommand;
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
    public BookCommand updateBook(BookCommand bookCommand) {

        Set<Pages> pages = bookRepository.findById(bookCommandToBook.convert(bookCommand).getId()).get().getPages();
        System.out.println("Pages size: " + pages);

        Set<PagesCommand> pagesCommands = new HashSet<>();
        pages.forEach(pages1 -> pagesCommands.add(pagesToPagesCommand.convert(pages1)));

        pagesCommands.forEach(pagesCommand -> pagesCommand.setBookId(bookCommand.getId()));

        bookCommand.setPages(pagesCommands);

        Book book = bookCommandToBook.convert(bookCommand);

        bookRepository.save(book);
        return bookToBookCommand.convert(book);
    }

    @Override
    public Set<Book> listBooks() {
        Set<Book> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(books::add);
        return books;
    }

    @Override
    public Book bookInfoById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent())
        {
            throw new RuntimeException("Book not found!");
        }

        return optionalBook.get();
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
}
