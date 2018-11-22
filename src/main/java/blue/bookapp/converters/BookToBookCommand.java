package blue.bookapp.converters;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.domain.Book;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BookToBookCommand implements Converter<Book, BookCommand> {

    private PagesToPagesCommand pagesToPagesCommand;

    public BookToBookCommand(PagesToPagesCommand pagesToPagesCommand) {
        this.pagesToPagesCommand = pagesToPagesCommand;
    }

    @Synchronized
    @Nullable
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
        bookCommand.setEAN(book.getEAN());
        bookCommand.setYear(book.getYear());
        bookCommand.setDate(book.getDate());
        bookCommand.setImage(book.getImage());
        bookCommand.setAuthor(book.getAuthor());
        bookCommand.setPublisher(book.getPublisher());
        bookCommand.setGenre(book.getGenre());

        if (book.getPages() != null && book.getPages().size() > 0)
        {
            book.getPages()
                    .forEach(pages -> bookCommand.getPages().add(pagesToPagesCommand.convert(pages)));
        }

        return bookCommand;
    }
}
