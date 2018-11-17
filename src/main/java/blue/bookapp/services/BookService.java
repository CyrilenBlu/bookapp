package blue.bookapp.services;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.domain.Book;
import blue.bookapp.domain.Publisher;

import java.math.BigDecimal;
import java.util.Set;

public interface BookService {

    BookCommand findCommandById(Long id);

    Set<Book> listBooksByAuthorName(String author);

    Set<Book> listBooksByPublisher(Publisher publisher);

    Set<Book> listBooksByYear(int year);

    void findByEAN(BigDecimal ean);

    Set<Book> listByTitle(String title);

    void addBook(BookCommand bookCommand);

    void removeBookById(Long id);

    Set<Book> listBooks();

    Book bookInfoById(Long id);

    BookCommand updateBookById(BookCommand bookCommand);
}
