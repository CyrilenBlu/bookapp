package blue.bookapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Getter@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;

    private BigDecimal price;
    private BigDecimal EAN;
    private Year year;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    @ManyToOne(cascade = CascadeType.ALL)
    private Publisher publisher;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "page")
    private Set<Pages> pages = new HashSet<>();

    public void setAuthor(Author author) {
        this.author = author;
        author.getBooks().add(this);
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
        publisher.getBooks().add(this);
    }
}
