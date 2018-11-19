package blue.bookapp.converters;

import blue.bookapp.commands.PagesCommand;
import blue.bookapp.domain.Pages;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PagesToPagesCommand implements Converter<Pages, PagesCommand> {

    private BookToBookCommand bookToBookCommand;

    public PagesToPagesCommand(BookToBookCommand bookToBookCommand) {
        this.bookToBookCommand = bookToBookCommand;
    }

    @Override
    public PagesCommand convert(Pages pages) {
        if (pages == null)
        {
            return null;
        }
        PagesCommand pagesCommand = new PagesCommand();
        pagesCommand.setId(pages.getId());
        pagesCommand.setPage(pages.getPage());
        pagesCommand.setChapter(pages.getChapter());
        pagesCommand.setContent(pages.getContent());

        if (pages.getBook() != null)
        {
            pagesCommand.setBookCommand(bookToBookCommand.convert(pages.getBook()));
        }
        
        return  pagesCommand;
    }
}
