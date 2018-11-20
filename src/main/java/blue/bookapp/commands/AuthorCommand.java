package blue.bookapp.commands;

import blue.bookapp.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter@Setter
@NoArgsConstructor
public class AuthorCommand {
    private Long id;
    private String name;
    private int age;
    private Set<Book> books = new HashSet<>();
}
