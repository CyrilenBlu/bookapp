package blue.bookapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter
@Entity
public class Pages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private int page;

    private String title;

    @Lob
    private String content;

    @ManyToOne
    private Book book;
}
