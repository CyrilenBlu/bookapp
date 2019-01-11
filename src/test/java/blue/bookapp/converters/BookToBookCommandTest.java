package blue.bookapp.converters;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class BookToBookCommandTest {

    private static final Long ID_VAL = 1L;
    private static final String DESCRIPTION = "Foo";
    @Mock
    PagesToPagesCommand pagesToPagesCommand;

    BookToBookCommand bookToBookCommand;

    @Before
    public void setUp() throws Exception {
        bookToBookCommand = new BookToBookCommand(pagesToPagesCommand);
    }

    @Test
    public void testEmpty() {
        assertNotNull(bookToBookCommand.convert(new Book()));
    }

    @Test
    public void testNull() {
        assertNull(bookToBookCommand.convert(null));
    }

    @Test
    public void convert() {
        Book book = new Book();
        book.setId(ID_VAL);
        book.setDescription(DESCRIPTION);

        BookCommand bookCommand = bookToBookCommand.convert(book);

        assertEquals(bookCommand.getId(), ID_VAL);
        assertEquals(bookCommand.getDescription(), DESCRIPTION);
    }
}