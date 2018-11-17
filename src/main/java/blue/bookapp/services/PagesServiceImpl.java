package blue.bookapp.services;

import blue.bookapp.domain.Book;
import blue.bookapp.domain.Pages;
import blue.bookapp.repositories.BookRepository;
import blue.bookapp.repositories.PagesRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PagesServiceImpl implements PagesService {
    private PagesRepository pagesRepository;
    private BookRepository bookRepository;

    public PagesServiceImpl(PagesRepository pagesRepository, BookRepository bookRepository) {
        this.pagesRepository = pagesRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void addPages(Set<Pages> pages) {
        pagesRepository.saveAll(pages);
    }

    @Override
    public Set<Pages> listPagesByBookId(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        Book book = optionalBook.get();

        return book.getPages();
    }

    @Override
    public Pages getPagesByBookById(Long id, Long pageNumber) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (!bookOptional.isPresent())
        {
            throw new RuntimeException("Book not found!");
        }
        Set<Pages> pages = bookOptional.get().getPages();
        Set<Pages> savedPages = new HashSet<>();
        pages.stream().forEach(pages1 ->
        {
            if (pages1.getPage() == pageNumber && bookOptional.get().getPages().contains(pages1))
            {
                savedPages.add(pages1);
            }
        });

        return savedPages.iterator().next();
    }
}
