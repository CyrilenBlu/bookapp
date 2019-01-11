package blue.bookapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdminTest {

    Admin admin;

    @Before
    public void setUp() throws Exception {
        admin = new Admin();
    }

    @Test
    public void getId() {
        Long idValue = 4L;
        admin.setId(idValue);
        assertEquals(Long.valueOf(4L), admin.getId());
    }
}