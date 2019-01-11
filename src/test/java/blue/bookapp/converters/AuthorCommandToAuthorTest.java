package blue.bookapp.converters;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.domain.Author;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class AuthorCommandToAuthorTest {

    private static final Long ID_VAL = 1L;
    private static final String NAME = "Jake";
    private static final int AGE = 19;
    AuthorCommandToAuthor authorCommandToAuthor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        authorCommandToAuthor = new AuthorCommandToAuthor();
    }

    @Test
    public void testEmpty() {
        assertNotNull(authorCommandToAuthor.convert(new AuthorCommand()));
    }

    @Test
    public void testNull() {
        assertNull(authorCommandToAuthor.convert(null));
    }

    @Test
    public void convert() {
        AuthorCommand authorCommand = new AuthorCommand();
        authorCommand.setId(ID_VAL);
        authorCommand.setName(NAME);
        authorCommand.setAge(AGE);

        Author author = authorCommandToAuthor.convert(authorCommand);

        assertEquals(author.getId(), ID_VAL);
        assertEquals(author.getName(), NAME);
        assertEquals(author.getAge(), AGE);
    }
}