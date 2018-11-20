package blue.bookapp.converters;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.domain.Author;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AuthorToAuthorCommand implements Converter<Author, AuthorCommand> {

    private BookToBookCommand bookToBookCommand;

        public AuthorToAuthorCommand(BookToBookCommand bookToBookCommand) {
            this.bookToBookCommand = bookToBookCommand;
        }


        @Synchronized
    @Nullable
    @Override
    public AuthorCommand convert(Author author) {
        if (author == null)
        {
            return null;
        }

        final AuthorCommand authorCommand = new AuthorCommand();
        authorCommand.setId(author.getId());
        authorCommand.setName(author.getName());
        authorCommand.setAge(author.getAge());
        authorCommand.setBooks(author.getBooks());

        return authorCommand;
    }
}
