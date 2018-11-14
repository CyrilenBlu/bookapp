package blue.bookapp.converters;

import blue.bookapp.commands.PagesCommand;
import blue.bookapp.domain.Pages;
import org.springframework.core.convert.converter.Converter;

public class PagesToPagesCommand implements Converter<Pages, PagesCommand> {
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

        return  pagesCommand;
    }
}
