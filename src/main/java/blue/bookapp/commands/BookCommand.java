package blue.bookapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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
}
