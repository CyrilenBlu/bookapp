package blue.bookapp.services;

import blue.bookapp.domain.Book;
import blue.bookapp.domain.Publisher;

import java.math.BigDecimal;
import java.util.Set;

public interface BookService {

    Set<Book> listBooksByAuthorName(String author);

    Set<Book> listBooksByPublisher(Publisher publisher);

    void listBooksByYear(int year);

    void findByEAN(BigDecimal ean);

    void listByTitle(String title);

    void addBook(Book book);

    void removeBookById(Long id);

    void updateBookById(Long id);
}
