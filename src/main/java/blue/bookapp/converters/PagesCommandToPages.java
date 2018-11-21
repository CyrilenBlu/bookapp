package blue.bookapp.converters;

import blue.bookapp.commands.PagesCommand;
import blue.bookapp.domain.Book;
import blue.bookapp.domain.Pages;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PagesCommandToPages implements Converter<PagesCommand, Pages> {
    @Synchronized
    @Nullable
    @Override
    public Pages convert(PagesCommand pagesCommand) {
        if (pagesCommand == null)
        {
            return null;
        }
        final Pages pages = new Pages();
        pages.setId(pagesCommand.getId());
        if (pagesCommand.getBookId() != null)
        {
            Book book = new Book();
            book.setId(pagesCommand.getBookId());
            pages.setBook(book);
            book.getPages().add(pages);
        }
        pages.setPage(pagesCommand.getPage());
        pages.setTitle(pagesCommand.getTitle());
        pages.setContent(pagesCommand.getContent());

        return pages;
    }
}
