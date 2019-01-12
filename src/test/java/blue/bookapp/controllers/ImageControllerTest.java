package blue.bookapp.controllers;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.services.BookService;
import blue.bookapp.services.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {
    @Mock
    ImageService imageService;
    @Mock
    BookService bookService;
    ImageController controller;
    MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new ImageController(bookService, imageService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void getImageForm() throws Exception {
        //given
        BookCommand command = new BookCommand();
        command.setId(1L);
        when(bookService.findCommandById(anyLong())).thenReturn(command);
        //when
        mockMvc.perform(get("/book/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"));
        verify(bookService, times(1)).findCommandById(anyLong());
    }
    @Test
    public void handleImagePost() throws Exception {
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                        "Blu Books".getBytes());
        mockMvc.perform(multipart("/book/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/book/1/update"));
        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }
}