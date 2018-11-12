package blue.bookapp.services;

import blue.bookapp.domain.Pages;
import blue.bookapp.repositories.PagesRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PagesServiceImpl implements PagesService {
    PagesRepository pagesRepository;

    public PagesServiceImpl(PagesRepository pagesRepository) {
        this.pagesRepository = pagesRepository;
    }

    @Override
    public void addPages(Set<Pages> pages) {
        pagesRepository.saveAll(pages);
    }
}
