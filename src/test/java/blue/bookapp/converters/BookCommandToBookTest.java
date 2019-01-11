package blue.bookapp.converters;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class BookCommandToBookTest {

    private static final Long ID_VAL = 1L;
    private static final String DESCRIPTION = "test";
    @Mock
    PagesCommandToPages pagesCommandToPages;

    BookCommandToBook bookCommandToBook;

    @Before
    public void setUp() throws Exception {
        bookCommandToBook = new BookCommandToBook(pagesCommandToPages);
    }

    @Test
    public void testEmpty() {
        assertNotNull(bookCommandToBook.convert(new BookCommand()));
    }

    @Test
    public void testNull() {
        assertNull(bookCommandToBook.convert(null));
    }

    @Test
    public void convert() {
        BookCommand bookCommand = new BookCommand();
        bookCommand.setId(ID_VAL);
        bookCommand.setDescription(DESCRIPTION);

        Book book = bookCommandToBook.convert(bookCommand);

        assertEquals(book.getId(), ID_VAL);
        assertEquals(book.getDescription(), DESCRIPTION);
    }
}