package blue.bookapp.converters;

import blue.bookapp.commands.PagesCommand;
import blue.bookapp.domain.Pages;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PagesCommandToPages implements Converter<PagesCommand, Pages> {

    private BookCommandToBook bookCommandToBook;

    public PagesCommandToPages(BookCommandToBook bookCommandToBook) {
        this.bookCommandToBook = bookCommandToBook;
    }

    @Override
    public Pages convert(PagesCommand pagesCommand) {
        if (pagesCommand == null)
        {
            return null;
        }
        Pages pages = new Pages();
        pages.setId(pagesCommand.getId());
        pages.setPage(pagesCommand.getPage());
        pages.setChapter(pagesCommand.getChapter());
        pages.setContent(pagesCommand.getContent());

        if (pagesCommand.getBookCommand() != null)
        {
            pages.setBook(bookCommandToBook.convert(pagesCommand.getBookCommand()));
        }

        return pages;
    }
}
