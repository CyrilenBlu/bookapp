package blue.bookapp.converters;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.domain.Author;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthorToAuthorCommandTest {

    private static final Long ID_VAL = 1L;
    private static final String NAME = "Jack";
    private static final int AGE = 20;
    AuthorToAuthorCommand authorToAuthorCommand;

    @Before
    public void setUp() throws Exception {
        authorToAuthorCommand = new AuthorToAuthorCommand();
    }

    @Test
    public void testEmpty() {
        assertNotNull(authorToAuthorCommand.convert(new Author()));
    }

    @Test
    public void testNull() {
        assertNull(authorToAuthorCommand.convert(null));
    }

    @Test
    public void convert() {
        Author author = new Author();
        author.setId(ID_VAL);
        author.setName(NAME);
        author.setAge(AGE);

        AuthorCommand authorCommand = authorToAuthorCommand.convert(author);

        assertEquals(authorCommand.getId(), ID_VAL);
        assertEquals(authorCommand.getName(), NAME);
        assertEquals(authorCommand.getAge(), AGE);
    }
}