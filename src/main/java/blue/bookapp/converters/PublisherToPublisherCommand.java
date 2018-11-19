package blue.bookapp.converters;

import blue.bookapp.commands.PublisherCommand;
import blue.bookapp.domain.Publisher;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PublisherToPublisherCommand implements Converter<Publisher, PublisherCommand> {
    @Synchronized
    @Nullable
    @Override
    public PublisherCommand convert(Publisher publisher) {
        if (publisher == null)
        {
            return null;
        }
        final PublisherCommand publisherCommand = new PublisherCommand();
        publisherCommand.setId(publisher.getId());
        publisherCommand.setName(publisher.getName());
        publisherCommand.setCity(publisher.getCity());
        publisherCommand.setCountry(publisher.getCountry());
        publisherCommand.setDate(publisher.getDate());

        return publisherCommand;
    }
}
