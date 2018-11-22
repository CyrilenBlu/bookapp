package blue.bookapp.converters;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.domain.Book;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BookCommandToBook implements Converter<BookCommand, Book> {

    private final PagesCommandToPages pagesCommandToPages;

    public BookCommandToBook(PagesCommandToPages pagesCommandToPages) {
        this.pagesCommandToPages = pagesCommandToPages;
    }

    @Synchronized
    @Nullable
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
        book.setEAN(bookCommand.getEAN());
        book.setYear(bookCommand.getYear());
        book.setAuthor(bookCommand.getAuthor());
        book.setDate(bookCommand.getDate());
        book.setPublisher(bookCommand.getPublisher());
        book.setGenre(bookCommand.getGenre());

        if (bookCommand.getPages() != null && bookCommand.getPages().size() > 0)
            bookCommand.getPages().forEach(pagesCommand ->
                                                    book.getPages().add(pagesCommandToPages.convert(pagesCommand)));

        return book;
    }
}
