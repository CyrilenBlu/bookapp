package blue.bookapp.converters;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.domain.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorToAuthorCommand implements Converter<Author, AuthorCommand> {
    @Override
    public AuthorCommand convert(Author author) {
        if (author == null)
        {
            return null;
        }

        final AuthorCommand authorCommand = new AuthorCommand();
        authorCommand.setId(author.getId());
        authorCommand.setName(author.getName());

        return authorCommand;
    }
}
