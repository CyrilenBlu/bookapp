package blue.bookapp.converters;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.domain.Author;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorCommandToAuthor implements Converter<AuthorCommand, Author> {

    @Override
    public Author convert(AuthorCommand authorCommand) {
        if (authorCommand == null)
        {
            return null;
        }

        final Author author = new Author();
        author.setName(authorCommand.getName());
        author.setId(authorCommand.getId());

        return author;
    }
}
