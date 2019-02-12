package blue.bookapp.api.v1.model;

import lombok.Data;

@Data
public class PagesDTO {
    private Long id;
    private int page;
    private String title;
    private String content;
    private BookDTO bookDTO;
}
