package blue.bookapp.services;

import blue.bookapp.commands.PublisherCommand;
import blue.bookapp.converters.PublisherCommandToPublisher;
import blue.bookapp.converters.PublisherToPublisherCommand;
import blue.bookapp.domain.Publisher;
import blue.bookapp.repositories.PublisherRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PublisherServiceImplTest {

    @Mock
    private PublisherRepository publisherRepository;
    @Mock
    private PublisherCommandToPublisher publisherCommandToPublisher;
    @Mock
    private PublisherToPublisherCommand publisherToPublisherCommand;

    PublisherService publisherService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        publisherService = new PublisherServiceImpl(publisherRepository, publisherCommandToPublisher, publisherToPublisherCommand);
    }

    @Test
    public void testAddAuthor() {
        //given
        PublisherCommand publisherCommand = new PublisherCommand();
        publisherCommand.setId(1L);
        publisherCommand.setName("Jake");

        //when
        when(publisherCommandToPublisher.convert(any())).thenReturn(new Publisher());
        when(publisherToPublisherCommand.convert(any())).thenReturn(publisherCommand);

        //then
        PublisherCommand savedAuthor = publisherService.addPublisher(publisherCommand);
        assertEquals(Long.valueOf(1L), savedAuthor.getId());
        verify(publisherRepository, times(1)).save(any(Publisher.class));
    }

    @Test
    public void testDeleteAuthor() {
        Publisher publisher = new Publisher();
        publisher.setId(1L);
        when(publisherRepository.findById(anyLong())).thenReturn(Optional.of(publisher));
        publisherService.removeById(1L);

        assertEquals(0, publisherService.findAll().size());
    }

    @Test
    public void testFindCommandById() {
        Publisher publisher = new Publisher();
        publisher.setId(1L);
        Optional<Publisher> publisherOptional = Optional.of(publisher);

        when(publisherRepository.findById(anyLong())).thenReturn(publisherOptional);

        PublisherCommand publisherCommand = new PublisherCommand();
        publisherCommand.setId(1L);

        when(publisherToPublisherCommand.convert(any())).thenReturn(publisherCommand);

        PublisherCommand publisherById = publisherService.findPublisherById(1L);

        assertNotNull("Null author returned", publisherById);
    }

    @Test
    public void testUpdateAuthor() {
        PublisherCommand publisherCommand = new PublisherCommand();
        publisherCommand.setId(1L);

        Optional<Publisher> publisherOptional = Optional.of(new Publisher());
        Publisher savedPublisher = new Publisher();
        savedPublisher.setId(1L);

        when(publisherRepository.findById(anyLong())).thenReturn(publisherOptional);
        when(publisherRepository.save(any())).thenReturn(savedPublisher);

        PublisherCommand savedCommand = publisherService.updatePublisher(publisherCommand);

        assertEquals(Long.valueOf(1L), savedCommand.getId());
    }
}