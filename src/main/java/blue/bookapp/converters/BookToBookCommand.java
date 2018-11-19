package blue.bookapp.converters;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.domain.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookToBookCommand implements Converter<Book, BookCommand> {
    

    @Override
    public BookCommand convert(Book book) {
        if (book == null)
        {
            return null;
        }
        final BookCommand bookCommand = new BookCommand();
        bookCommand.setId(book.getId());
        bookCommand.setTitle(book.getTitle());
        bookCommand.setDescription(book.getDescription());
        bookCommand.setPrice(book.getPrice());
        bookCommand.setEAN(book.getEAN());
        bookCommand.setYear(book.getYear());
        bookCommand.setImage(book.getImage());
        bookCommand.setAuthor(book.getAuthor());
        bookCommand.setPublisher(book.getPublisher());
        bookCommand.setGenre(book.getGenre());


        return bookCommand;
    }
}
