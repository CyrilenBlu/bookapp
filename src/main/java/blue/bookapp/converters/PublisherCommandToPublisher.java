package blue.bookapp.converters;

import blue.bookapp.commands.PublisherCommand;
import blue.bookapp.domain.Publisher;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PublisherCommandToPublisher implements Converter<PublisherCommand, Publisher> {
    @Override
    public Publisher convert(PublisherCommand publisherCommand) {
        if (publisherCommand == null)
        {
            return null;
        }
        Publisher publisher = new Publisher();
        publisher.setId(publisherCommand.getId());
        publisher.setName(publisherCommand.getName());
        publisher.setCity(publisherCommand.getCity());
        publisher.setCountry(publisherCommand.getCountry());
        publisher.setDate(publisherCommand.getDate());

        return publisher;
    }
}
