package blue.bookapp.services;

import blue.bookapp.domain.Author;
import blue.bookapp.domain.Book;
import blue.bookapp.domain.Publisher;

import java.math.BigDecimal;
import java.time.Year;

public interface BookService {

    void listBooksByAuthor(Author author);

    void listBooksByPublisher(Publisher publisher);

    void listBooksByYear(Year year);

    void findByEAN(BigDecimal ean);

    void listByTitle(String title);

    void addBook(Book book);

    void removeBookById(Long id);

    void updateBookById(Long id);
}
