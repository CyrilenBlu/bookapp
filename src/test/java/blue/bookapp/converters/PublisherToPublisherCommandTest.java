package blue.bookapp.converters;

import blue.bookapp.commands.PublisherCommand;
import blue.bookapp.domain.Publisher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PublisherToPublisherCommandTest {

    private static final Long ID_VAL = 1L;
    private static final String NAME = "foo";
    PublisherToPublisherCommand publisherToPublisherCommand;

    @Before
    public void setUp() throws Exception {
        publisherToPublisherCommand = new PublisherToPublisherCommand();
    }

    @Test
    public void testEmpty() {
        assertNotNull(publisherToPublisherCommand.convert(new Publisher()));
    }

    @Test
    public void testNull() {
        assertNull(publisherToPublisherCommand.convert(null));
    }

    @Test
    public void convert() {
        Publisher publisher = new Publisher();
        publisher.setId(ID_VAL);
        publisher.setName(NAME);

        PublisherCommand publisherCommand = publisherToPublisherCommand.convert(publisher);

        assertEquals(publisherCommand.getId(), ID_VAL);
        assertEquals(publisherCommand.getName(), NAME);
    }
}