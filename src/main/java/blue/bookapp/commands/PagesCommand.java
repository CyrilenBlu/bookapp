package blue.bookapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class PagesCommand {
    private Long id;
    private Long bookId;
    private int page;
    private String title;
    private String content;
}
