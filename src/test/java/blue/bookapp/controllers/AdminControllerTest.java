package blue.bookapp.controllers;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.commands.PagesCommand;
import blue.bookapp.domain.*;
import blue.bookapp.services.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AdminControllerTest {

    @Mock
    private AdminService adminService;
    @Mock
    private BookService bookService;
    @Mock
    private AuthorService authorService;
    @Mock
    private PublisherService publisherService;
    @Mock
    private PagesService pagesService;

    AdminController controller;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new AdminController(adminService, bookService,
                authorService, publisherService, pagesService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListBooks_Admin() throws Exception {
        Set<Book> books = new HashSet<>();

        when(bookService.listBooks()).thenReturn(books);

        mockMvc.perform(get("/admin-book-list"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin-cpl"))
                .andExpect(model().attributeExists("books"));

        verify(bookService, times(1)).listBooks();
    }

    @Test
    public void testViewBook_Admin() throws Exception {
        BookCommand bookCommand = new BookCommand();
        Admin admin = new Admin();
        admin.setCheckLogged(true);

        when(bookService.findCommandById(anyLong())).thenReturn(bookCommand);

        mockMvc.perform(get("/book/1/show"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin-cpl"))
                .andExpect(model().attributeExists("book"));

        verify(bookService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void testListPages_Admin() throws Exception {
        Set<Pages> pages = new HashSet<>();
        BookCommand bookCommand = new BookCommand();

        when(pagesService.listPagesByBookId(anyLong())).thenReturn(pages);
        when(bookService.findCommandById(anyLong())).thenReturn(bookCommand);

        mockMvc.perform(get("/book/1/pages"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin-cpl"))
                .andExpect(model().attributeExists("pages"))
                .andExpect(model().attributeExists("book"));

        verify(pagesService, times(1)).listPagesByBookId(anyLong());
        verify(bookService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void testViewPage_Admin() throws Exception {
        BookCommand bookCommand = new BookCommand();
        PagesCommand pagesCommand = new PagesCommand();

        when(bookService.findCommandById(anyLong())).thenReturn(bookCommand);
        when(pagesService.getCommandByBookByIdAndPageId(anyLong(), anyLong())).thenReturn(pagesCommand);

        mockMvc.perform(get("/book/1/pages/1/update"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin-cpl"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("page"));

        verify(bookService, times(1)).findCommandById(anyLong());
        verify(pagesService, times(1)).getCommandByBookByIdAndPageId(anyLong(), anyLong());
    }

    @Test
    public void testListAuthors_Admin() throws Exception {
        Set<Author> authors = new HashSet<>();

        when(authorService.findAll()).thenReturn(authors);

        mockMvc.perform(get("/admin-author-list"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin-cpl"))
                .andExpect(model().attributeExists("authors"));

        verify(authorService, times(1)).findAll();
    }

    @Test
    public void testListPublishers_Admin() throws Exception {
        Set<Publisher> publishers = new HashSet<>();

        when(publisherService.findAll()).thenReturn(publishers);

        mockMvc.perform(get("/admin-publisher-list"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin-cpl"))
                .andExpect(model().attributeExists("publishers"));

        verify(publisherService, times(1)).findAll();
    }
}