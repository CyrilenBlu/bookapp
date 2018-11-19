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
        Optional<Book> optionalBook = bookRepository.findById(pagesCommand.getBookCommand().getId());
        if (!optionalBook.isPresent())
        {
            log.error("Book not found for id: " + pagesCommand.getId());
            return new PagesCommand();
        } else
        {
            Book book = optionalBook.get();

            Optional<Pages> pagesOptional =
                    book.getPages().stream()
                    .filter(pages -> pages.getId().equals(pagesCommand.getId()))
                    .findFirst();


            Pages pages = pagesOptional.get();
            pages.setPage(pagesCommand.getPage());
            pages.setChapter(pagesCommand.getChapter());
            pages.setContent(pagesCommand.getContent());

            Book savedBook = bookRepository.save(book);

            Optional<Pages> savedPagesOptional = savedBook.getPages()
                    .stream().filter(bookPages -> bookPages.getId().equals(pagesCommand.getId()))
                    .findFirst();
            //todo test
            return pagesToPagesCommand.convert(savedPagesOptional.get());

        }
    }
}
