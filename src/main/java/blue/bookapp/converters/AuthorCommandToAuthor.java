package blue.bookapp.converters;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.domain.Author;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AuthorCommandToAuthor implements Converter<AuthorCommand, Author> {

    private BookCommandToBook bookCommandToBook;

    public AuthorCommandToAuthor(BookCommandToBook bookCommandToBook) {
        this.bookCommandToBook = bookCommandToBook;
    }

    @Synchronized
    @Nullable
    @Override
    public Author convert(AuthorCommand authorCommand) {
        if (authorCommand == null)
        {
            return null;
        }

        final Author author = new Author();
        author.setName(authorCommand.getName());
        author.setId(authorCommand.getId());
        author.setAge(authorCommand.getAge());
        author.setBooks(authorCommand.getBooks());

        return author;
    }
}
