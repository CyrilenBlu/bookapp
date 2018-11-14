package blue.bookapp.converters;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.domain.Book;
import org.springframework.core.convert.converter.Converter;

public class BookCommandToBook implements Converter<BookCommand, Book> {
    @Override
    public Book convert(BookCommand bookCommand) {
        if (bookCommand == null)
        {
            return null;
        }
        final Book book = new Book();
        book.setId(bookCommand.getId());
        book.setTitle(bookCommand.getTitle());
        book.setDescription(bookCommand.getDescription());
        book.setPrice(bookCommand.getPrice());
        book.setEAN(bookCommand.getEAN());
        book.setYear(bookCommand.getYear());
        book.setImage(bookCommand.getImage());

        return book;
    }
}
