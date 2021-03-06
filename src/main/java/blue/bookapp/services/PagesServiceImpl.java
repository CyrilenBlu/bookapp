package blue.bookapp.services;

import blue.bookapp.commands.PagesCommand;
import blue.bookapp.converters.PagesCommandToPages;
import blue.bookapp.converters.PagesToPagesCommand;
import blue.bookapp.domain.Book;
import blue.bookapp.domain.Pages;
import blue.bookapp.repositories.BookRepository;
import blue.bookapp.repositories.PagesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class PagesServiceImpl implements PagesService {
    private PagesRepository pagesRepository;
    private BookRepository bookRepository;
    private PagesCommandToPages pagesCommandToPages;
    private PagesToPagesCommand pagesToPagesCommand;

    public PagesServiceImpl(PagesRepository pagesRepository, BookRepository bookRepository, PagesCommandToPages pagesCommandToPages, PagesToPagesCommand pagesToPagesCommand) {
        this.pagesRepository = pagesRepository;
        this.bookRepository = bookRepository;
        this.pagesCommandToPages = pagesCommandToPages;
        this.pagesToPagesCommand = pagesToPagesCommand;
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
    public PagesCommand getCommandByBookById(Long id, Long pageNumber) {
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

        return pagesToPagesCommand.convert(savedPages.iterator().next());
    }

    @Override
    public void removeById(Long bookId,Long pageId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent())
        {
            Book book = bookOptional.get();
            log.info("found book.");

            Optional<Pages> pagesOptional =
                    book.getPages().stream()
                    .filter(pages -> pages.getId().equals(pageId))
                    .findFirst();
            if (pagesOptional.isPresent())
            {
                log.info("found page");
                Pages pageToDelete = pagesOptional.get();
                pageToDelete.setBook(null);
                book.getPages().remove(pagesOptional.get());
                pagesRepository.deleteById(pageId);
                bookRepository.save(book);
            }
        } else{
            log.debug("Book ID not found. ID: " + bookId);
        }
    }

    @Override
    @Transactional
    public PagesCommand updatePageCommand(PagesCommand pagesCommand) {

        Pages pages = pagesCommandToPages.convert(pagesCommand);
        Pages savedPage = pagesRepository.save(pages);
        return pagesToPagesCommand.convert(savedPage);
    }


    @Override
    public PagesCommand getCommandByBookByIdAndPageId(Long bookId, Long pageId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent())
        {
            throw new RuntimeException("Book not found!");
        }
        Set<Pages> pages = bookOptional.get().getPages();
        Set<Pages> savedPages = new HashSet<>();
        pages.stream().forEach(pages1 ->
        {
            if (pages1.getId() == pageId && bookOptional.get().getPages().contains(pages1))
            {
                savedPages.add(pages1);
            }
        });

        return pagesToPagesCommand.convert(savedPages.iterator().next());
    }

    @Override
    public PagesCommand addCommand(PagesCommand pagesCommand) {
        Pages pages = pagesCommandToPages.convert(pagesCommand);
        if (pages.getId() == null)
        {
            pagesRepository.save(pages);
        }
        Optional<Book> bookOptional = bookRepository.findById(pagesCommand.getBookId());
        if (!bookOptional.isPresent())
            throw new RuntimeException("Book not found!");
        Book book = bookOptional.get();

        book.getPages().add(pages);

        bookRepository.save(book);

        return pagesToPagesCommand.convert(pages);
    }
}
