package blue.bookapp.services;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.converters.AuthorCommandToAuthor;
import blue.bookapp.converters.AuthorToAuthorCommand;
import blue.bookapp.domain.Author;
import blue.bookapp.repositories.AuthorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AuthorServiceImplTest {

    @Mock
    AuthorService authorService;

    @Mock
    AuthorRepository authorRepository;

    @Mock
    AuthorCommandToAuthor authorCommandToAuthor;

    @Mock
    AuthorToAuthorCommand authorToAuthorCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        authorService = new AuthorServiceImpl(authorRepository, authorCommandToAuthor, authorToAuthorCommand);
    }

    @Test
    public void testAddAuthor() {
        //given
        AuthorCommand authorCommand = new AuthorCommand();
        authorCommand.setId(1L);
        authorCommand.setName("Jake");
        authorCommand.setAge(5);

        //when
        when(authorCommandToAuthor.convert(any())).thenReturn(new Author());
        when(authorToAuthorCommand.convert(any())).thenReturn(authorCommand);

        //then
        AuthorCommand savedAuthor = authorService.addAuthor(authorCommand);
        assertEquals(Long.valueOf(1L), savedAuthor.getId());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    public void testDeleteAuthor() {
        Author author = new Author();
        author.setId(1L);
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));
        authorService.removeById(1L);

        assertEquals(0, authorService.findAll().size());
    }

    @Test
    public void testFindCommandById() {
        Author author = new Author();
        author.setId(1L);
        Optional<Author> authorOptional = Optional.of(author);

        when(authorRepository.findById(anyLong())).thenReturn(authorOptional);

        AuthorCommand authorCommand = new AuthorCommand();
        authorCommand.setId(1L);

        when(authorToAuthorCommand.convert(any())).thenReturn(authorCommand);

        AuthorCommand authorById = authorService.findCommandById(1L);

        assertNotNull("Null author returned", authorById);
    }

    @Test
    public void testUpdateAuthor() {
        AuthorCommand authorCommand = new AuthorCommand();
        authorCommand.setId(1L);

        Optional<Author> authorOptional = Optional.of(new Author());
        Author savedAuthor = new Author();
        savedAuthor.setId(1L);

        when(authorRepository.findById(anyLong())).thenReturn(authorOptional);
        when(authorRepository.save(any())).thenReturn(savedAuthor);

        AuthorCommand savedCommand = authorService.updateAuthor(authorCommand);

        assertEquals(Long.valueOf(1L), savedCommand.getId());
    }
}