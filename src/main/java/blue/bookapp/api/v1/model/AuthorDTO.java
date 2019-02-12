package blue.bookapp.api.v1.model;

import lombok.Data;

@Data
public class AuthorDTO {
    private Long id;
    private String name;
    private int age;

}
