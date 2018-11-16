package blue.bookapp.services;

import blue.bookapp.domain.Pages;

import java.util.Set;

public interface PagesService {
    void addPages(Set<Pages> pages);
    Set<Pages> listPagesByBookId(Long id);
    Pages getPagesByBookById(Long id, Long pageNumber);
}
