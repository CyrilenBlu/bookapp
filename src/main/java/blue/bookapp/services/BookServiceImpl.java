package blue.bookapp.services;

import blue.bookapp.domain.Book;
import blue.bookapp.domain.Publisher;
import blue.bookapp.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
    public void listBooksByYear(int year) {
        bookRepository.findAll().forEach(book -> new Integer(year).equals(book.getYear()));
    }

    @Override
    public void findByEAN(BigDecimal ean) {
        bookRepository.findAll().forEach(book -> book.getEAN().equals(ean));
        log.debug("Found EAN");
    }

    @Override
    public void listByTitle(String title) {
        bookRepository.findAll().forEach(book -> book.getTitle().equals(title));
        log.debug("Found Title");
    }

    @Override
    public void addBook(Book book) {
        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        if (!optionalBook.isPresent())
        {
            throw new RuntimeException("Book already exists");
        }
        log.debug("Saving new book.");
        bookRepository.save(book);
    }

    @Override
    public void removeBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void updateBookById(Long id) {

    }
}
