package blue.bookapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class PagesCommand {
    private Long id;
    private int page;
    private String chapter;
    private String content;
}
