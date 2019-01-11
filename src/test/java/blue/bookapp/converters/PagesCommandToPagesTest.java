package blue.bookapp.converters;

import blue.bookapp.commands.PagesCommand;
import blue.bookapp.domain.Pages;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PagesCommandToPagesTest {

    private static final Long ID_VAL = 1L;
    private static final String TITLE = "Foo";
    PagesCommandToPages pagesCommandToPages;

    @Before
    public void setUp() throws Exception {
        pagesCommandToPages = new PagesCommandToPages();
    }

    @Test
    public void testEmpty() {
        assertNotNull(pagesCommandToPages.convert(new PagesCommand()));
    }

    @Test
    public void testNull() {
        assertNull(pagesCommandToPages.convert(null));
    }

    @Test
    public void convert() {
        PagesCommand pagesCommand = new PagesCommand();
        pagesCommand.setId(ID_VAL);
        pagesCommand.setTitle(TITLE);

        Pages pages = pagesCommandToPages.convert(pagesCommand);

        assertEquals(pages.getId(), ID_VAL);
        assertEquals(pages.getTitle(), TITLE);
    }
}