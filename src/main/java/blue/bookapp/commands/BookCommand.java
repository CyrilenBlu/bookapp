package blue.bookapp.commands;

import blue.bookapp.domain.Author;
import blue.bookapp.domain.Genre;
import blue.bookapp.domain.Publisher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter@Setter
@NoArgsConstructor
public class BookCommand {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Long EAN;
    private int year;
    private Byte[] image;
    private Author author;
    private Publisher publisher;
    private Genre genre;
    private Set<PagesCommand> pagesCommands = new HashSet<>();
}
