package blue.bookapp.services;

import blue.bookapp.domain.Book;
import blue.bookapp.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {

    @Mock
    BookRepository bookRepository;

    ImageService imageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImpl(bookRepository);
    }

    @Test
    public void saveImageFile() throws Exception {
        //given
        Long id = 1L;
        MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Spring Framework Guru".getBytes());

        Book book = new Book();
        book.setId(id);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        ArgumentCaptor<Book> argumentCaptor = ArgumentCaptor.forClass(Book.class);

        //when
        imageService.saveImageFile(id, multipartFile);

        //then
        verify(bookRepository, times(1)).save(argumentCaptor.capture());
        Book savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }

}