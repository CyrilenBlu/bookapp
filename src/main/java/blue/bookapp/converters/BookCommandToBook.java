package blue.bookapp.converters;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.domain.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookCommandToBook implements Converter<BookCommand, Book> {

    private PagesCommandToPages pagesCommandToPages;

    public BookCommandToBook(PagesCommandToPages pagesCommandToPages) {
        this.pagesCommandToPages = pagesCommandToPages;
    }

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
        book.setAuthor(bookCommand.getAuthor());
        book.setPublisher(bookCommand.getPublisher());
        book.setGenre(bookCommand.getGenre());

        if (bookCommand.getPagesCommands() != null && bookCommand.getPagesCommands().size() > 0)
            bookCommand.getPagesCommands().forEach(pagesCommand ->
                                                    book.getPages().add(pagesCommandToPages.convert(pagesCommand)));



        return book;
    }
}
