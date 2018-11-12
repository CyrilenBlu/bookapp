package blue.bookapp.services;

import blue.bookapp.domain.Author;
import blue.bookapp.domain.Book;
import blue.bookapp.domain.Publisher;
import blue.bookapp.repositories.BookRepository;
import blue.bookapp.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void listBooksByAuthor(Author author) {

    }

    @Override
    public void listBooksByPublisher(Publisher publisher) {

    }

    @Override
    public void listBooksByYear(Year year) {

    }

    @Override
    public void findByEAN(BigDecimal ean) {

    }

    @Override
    public void listByTitle(String title) {

    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void removeBookById(Long id) {

    }

    @Override
    public void updateBookById(Long id) {

    }
}
