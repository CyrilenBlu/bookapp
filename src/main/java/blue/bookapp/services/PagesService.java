package blue.bookapp.services;

import blue.bookapp.commands.PagesCommand;
import blue.bookapp.domain.Pages;

import java.util.Set;

public interface PagesService {
    void addPages(Set<Pages> pages);
    Set<Pages> listPagesByBookId(Long id);
    PagesCommand getCommandByBookById(Long id, Long pageNumber);
    void removeById(Long bookId,Long pageId);
    PagesCommand updatePageCommand(PagesCommand pagesCommand);
    PagesCommand getCommandByBookByIdAndPageId(Long bookId, Long pageId);
    PagesCommand addCommand(PagesCommand pagesCommand);
}
