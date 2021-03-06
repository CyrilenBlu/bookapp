package blue.bookapp.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
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

    private Long EAN;
    private int year;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Lob
    private Byte[] image;

    @ManyToOne(cascade = CascadeType.ALL)
    @Nullable
    private Author author;

    @ManyToOne(cascade = CascadeType.ALL)
    @Nullable
    private Publisher publisher;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "book_pages",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "pages_id"))
    private Set<Pages> pages = new HashSet<>();

    public void setAuthorOnly(Author authorOnly)
    {
        this.author = authorOnly;
    }

    public void setPublisherOnly(Publisher publisherOnly)
    {
        this.publisher = publisherOnly;
    }

    public void setAuthor(Author author) {
        this.author = author;
        if (author != null)
            author.getBooks().add(this);
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
        if (publisher != null)
            publisher.getBooks().add(this);
    }
}
