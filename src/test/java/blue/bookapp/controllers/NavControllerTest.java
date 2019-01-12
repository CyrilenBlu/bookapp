package blue.bookapp.controllers;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.services.BookService;
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

public class NavControllerTest {

    @Mock
    BookService bookService;

    NavController controller;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new NavController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void indexPage() throws Exception {
        mockMvc.perform(get("/homepage"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void listPage() throws Exception {
        Set<BookCommand> books = new HashSet<>();

        when(bookService.listBooks()).thenReturn(books);

        mockMvc.perform(get("/listpage"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/list"))
                .andExpect(model().attributeExists("books"));

        verify(bookService, times(1)).listBooks();
    }

    @Test
    public void contactPage() throws Exception {
        mockMvc.perform(get("/contactpage"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));
    }
}