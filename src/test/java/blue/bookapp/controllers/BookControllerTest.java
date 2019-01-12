package blue.bookapp.controllers;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.commands.PagesCommand;
import blue.bookapp.domain.Pages;
import blue.bookapp.services.BookService;
import blue.bookapp.services.PagesService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;
    @Mock
    private PagesService pagesService;

    BookController controller;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new BookController(bookService, pagesService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void viewBook() throws Exception {
        BookCommand bookCommand = new BookCommand();
        Set<Pages> pagesSet = new HashSet<>();

        when(bookService.findCommandById(anyLong())).thenReturn(bookCommand);
        when(pagesService.listPagesByBookId(anyLong())).thenReturn(pagesSet);

        mockMvc.perform(get("/book/1/info"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/viewBook"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("page"));

        verify(bookService, times(1)).findCommandById(anyLong());
        verify(pagesService, times(1)).listPagesByBookId(anyLong());
    }

    @Test
    public void readBook() throws Exception {
        BookCommand bookCommand = new BookCommand();
        Set<Pages> pages = new HashSet<>();
        PagesCommand pagesCommand = new PagesCommand();

        when(bookService.findCommandById(anyLong())).thenReturn(bookCommand);
        when(pagesService.listPagesByBookId(anyLong())).thenReturn(pages);
        when(pagesService.getCommandByBookById(anyLong(), anyLong())).thenReturn(pagesCommand);

        mockMvc.perform(get("/book/1/read/page/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/read"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("pages"))
                .andExpect(model().attributeExists("page"));


        verify(bookService, times(1)).findCommandById(anyLong());
        verify(pagesService, times(1)).listPagesByBookId(anyLong());
        verify(pagesService, times(1)).getCommandByBookById(anyLong(), anyLong());
    }
}