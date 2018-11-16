package blue.bookapp.services;

import blue.bookapp.domain.Book;
import blue.bookapp.domain.Pages;
import blue.bookapp.repositories.BookRepository;
import blue.bookapp.repositories.PagesRepository;
import org.springframework.stereotype.Service;

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
    public Pages getPagesByBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        Book book = bookOptional.get();

        Set<Pages> pagesSet = book.getPages();

        return pagesSet.stream().findFirst().get();

    }
}
