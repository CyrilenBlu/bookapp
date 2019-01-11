package blue.bookapp.services;

import blue.bookapp.commands.PagesCommand;
import blue.bookapp.converters.PagesCommandToPages;
import blue.bookapp.converters.PagesToPagesCommand;
import blue.bookapp.domain.Book;
import blue.bookapp.domain.Pages;
import blue.bookapp.repositories.BookRepository;
import blue.bookapp.repositories.PagesRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PagesServiceImplTest {

    @Mock
    PagesRepository pagesRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    PagesCommandToPages pagesCommandToPages;
    @Mock
    PagesToPagesCommand pagesToPagesCommand;

    PagesService pagesService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        pagesService = new PagesServiceImpl(pagesRepository, bookRepository, pagesCommandToPages, pagesToPagesCommand);
    }

    @Test
    public void testAddPages() {
        Set<Pages> pages = new HashSet<>();
        pages.add(new Pages());

        pagesService.addPages(pages);

        verify(pagesRepository, times(1)).saveAll(any());
    }

    @Test
    public void testListPages() {
        Set<Pages> pages = new HashSet<>();
        pages.add(new Pages());

        Book book = new Book();
        book.setId(1L);
        book.setPages(pages);
        Optional<Book> bookOptional = Optional.of(book);

        when(bookRepository.findById(1L)).thenReturn(bookOptional);

        Set<Pages> savedPages = pagesService.listPagesByBookId(1L);

        assertEquals(1, savedPages.size());
        assertNull(savedPages.iterator().next().getId());
    }

    @Test
    public void testGetCommandByBookId() {
        Set<Pages> pagesSet = new HashSet<>();
        Pages pages = new Pages();
        pages.setId(1L);
        pages.setPage(1);
        pagesSet.add(pages);

        Book book = new Book();
        book.setId(1L);
        book.setPages(pagesSet);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        PagesCommand pagesCommand = new PagesCommand();
        pagesCommand.setId(1L);

        when(pagesToPagesCommand.convert(any())).thenReturn(pagesCommand);

        PagesCommand savedPages = pagesService.getCommandByBookById(book.getId(), (long) pages.getPage());

        assertEquals(Long.valueOf(1), savedPages.getId());


    }
}