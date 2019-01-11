package blue.bookapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    Book book;

    @Before
    public void setUp() throws Exception {
        book = new Book();
    }

    @Test
    public void getId() {
        Long idValue = 3L;
        book.setId(idValue);
        assertEquals(Long.valueOf(3L), book.getId());
    }
}