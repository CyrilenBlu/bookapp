package blue.bookapp.converters;

import blue.bookapp.commands.PublisherCommand;
import blue.bookapp.domain.Publisher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PublisherCommandToPublisherTest {

    private static final Long ID_VAL = 1L;
    private static final String NAME = "Foo";
    PublisherCommandToPublisher publisherCommandToPublisher;

    @Before
    public void setUp() throws Exception {
        publisherCommandToPublisher = new PublisherCommandToPublisher();
    }

    @Test
    public void testEmpty() {
        assertNotNull(publisherCommandToPublisher.convert(new PublisherCommand()));
    }

    @Test
    public void testNull() {
        assertNull(publisherCommandToPublisher.convert(null));
    }

    @Test
    public void convert() {
        PublisherCommand publisherCommand = new PublisherCommand();
        publisherCommand.setId(ID_VAL);
        publisherCommand.setName(NAME);

        Publisher publisher = publisherCommandToPublisher.convert(publisherCommand);

        assertEquals(publisher.getId(), ID_VAL);
        assertEquals(publisher.getName(), NAME);
    }
}