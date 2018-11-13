package blue.bookapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class AuthorCommand {
    private Long id;
    private String name;
}
