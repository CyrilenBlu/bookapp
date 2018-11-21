package blue.bookapp.converters;

import blue.bookapp.commands.PagesCommand;
import blue.bookapp.domain.Pages;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PagesToPagesCommand implements Converter<Pages, PagesCommand> {
    @Synchronized
    @Nullable
    @Override
    public PagesCommand convert(Pages pages) {
        if (pages == null)
        {
            return null;
        }
        final PagesCommand pagesCommand = new PagesCommand();
        pagesCommand.setId(pages.getId());
        if (pages.getBook() != null)
        {
            pagesCommand.setBookId(pages.getBook().getId());
        }
        pagesCommand.setPage(pages.getPage());
        pagesCommand.setTitle(pages.getTitle());
        pagesCommand.setContent(pages.getContent());
        
        return  pagesCommand;
    }
}
