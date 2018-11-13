package blue.bookapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
@NoArgsConstructor
public class PublisherCommand {
    private Long id;
    private String name;
    private String city;
    private String country;
    private LocalDate date;
}
