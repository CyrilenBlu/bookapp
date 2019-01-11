package blue.bookapp.services;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.converters.BookCommandToBook;
import blue.bookapp.converters.BookToBookCommand;
import blue.bookapp.converters.PagesToPagesCommand;
import blue.bookapp.domain.Book;
import blue.bookapp.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookService bookService;

    @Mock
    BookToBookCommand bookToBookCommand;

    @Mock
    BookCommandToBook bookCommandToBook;

    @Mock
    PagesToPagesCommand pagesToPagesCommand;

    @Mock
    EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bookService = new BookServiceImpl(bookRepository, bookCommandToBook, bookToBookCommand, pagesToPagesCommand, entityManager);
    }

    @Test
    public void testAddBook() {
        //given
        Book book = new Book();
        book.setId(1L);

        BookCommand bookCommand = new BookCommand();
        bookCommand.setId(1L);

        //when
        when(bookCommandToBook.convert(any())).thenReturn(book);
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(bookToBookCommand.convert(any())).thenReturn(bookCommand);

        //then
        BookCommand savedBook = bookService.addBook(bookCommand);

        assertEquals(Long.valueOf(1L), savedBook.getId());
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(bookRepository, times(1)).findById(anyLong());
    }
}