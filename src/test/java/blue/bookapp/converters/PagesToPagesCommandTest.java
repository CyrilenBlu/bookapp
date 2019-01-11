package blue.bookapp.converters;

import blue.bookapp.commands.PagesCommand;
import blue.bookapp.domain.Pages;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PagesToPagesCommandTest {

    private static final Long ID_VAL = 1L;
    private static final String TITLE = "Fooo";
    PagesToPagesCommand pagesToPagesCommand;

    @Before
    public void setUp() throws Exception {
        pagesToPagesCommand = new PagesToPagesCommand();
    }

    @Test
    public void testEmpty() {
        assertNotNull(pagesToPagesCommand.convert(new Pages()));
    }

    @Test
    public void testNull() {
        assertNull(pagesToPagesCommand.convert(null));
    }

    @Test
    public void convert() {
        Pages pages = new Pages();
        pages.setId(ID_VAL);
        pages.setTitle(TITLE);

        PagesCommand pagesCommand = pagesToPagesCommand.convert(pages);

        assertEquals(pagesCommand.getId(), ID_VAL);
        assertEquals(pagesCommand.getTitle(), TITLE);
    }
}