package blue.bookapp.api.v1.model;

import lombok.Data;

@Data
public class PublisherDTO {
    private Long id;
    private String name;
    private String city;
    private String country;
}
