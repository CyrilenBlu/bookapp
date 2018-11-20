package blue.bookapp.commands;

import blue.bookapp.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter@Setter
@NoArgsConstructor
public class PublisherCommand {
    private Long id;
    private String name;
    private String city;
    private String country;
    private LocalDate date;
    private Set<Book> books = new HashSet<>();
}
