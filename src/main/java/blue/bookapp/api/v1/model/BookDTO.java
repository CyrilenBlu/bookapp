package blue.bookapp.api.v1.model;

import lombok.Data;

import java.util.Set;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String description;
    private Long EAN;
    private int year;
    private GenreDTO genreDTO;
    private Byte[] image;
    private AuthorDTO authorDTO;
    private PublisherDTO publisherDTO;
    private Set<PagesDTO> pagesDTOS;
}
