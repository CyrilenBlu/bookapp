package blue.bookapp.api.v1.mapping;

import blue.bookapp.api.v1.model.PublisherDTO;
import blue.bookapp.domain.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    PublisherDTO publisherToPublisherDTO(Publisher publisher);

    Publisher publisherDtoToPublisher(PublisherDTO publisherDTO);
}
