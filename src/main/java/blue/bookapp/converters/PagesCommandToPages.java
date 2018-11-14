package blue.bookapp.converters;

import blue.bookapp.commands.PagesCommand;
import blue.bookapp.domain.Pages;
import org.springframework.core.convert.converter.Converter;

public class PagesCommandToPages implements Converter<PagesCommand, Pages> {
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

        return pages;
    }
}
