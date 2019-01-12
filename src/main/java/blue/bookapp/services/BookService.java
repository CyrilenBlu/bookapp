package blue.bookapp.services;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.domain.Publisher;

import java.math.BigDecimal;
import java.util.Set;

public interface BookService {

    BookCommand findCommandById(Long id);

    Set<BookCommand> listBooksByAuthorName(String author);

    Set<BookCommand> listBooksByPublisher(Publisher publisher);

    Set<BookCommand> listBooksByYear(int year);

    void findByEAN(BigDecimal ean);

    Set<BookCommand> listByTitle(String title);

    BookCommand addBook(BookCommand bookCommand);

    BookCommand updateAuthorPublisher(BookCommand bookCommand);

    void removeBookById(Long id);

    Set<BookCommand> listBooks();

    BookCommand updateBook(BookCommand bookCommand);
}
