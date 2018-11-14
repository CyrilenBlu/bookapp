package blue.bookapp.bootstrap;

import blue.bookapp.domain.*;
import blue.bookapp.repositories.BookRepository;
import blue.bookapp.services.BookService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BookBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private BookRepository bookRepository;
    private BookService bookService;


    public BookBootstrap(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        bookRepository.saveAll(getBooks());
        //System.out.println(bookService.listBooksByAuthorName("test"));
        //System.out.println(bookService.listBooksByAuthorName("Jake Sorey"));
    }

    private List<Book> getBooks()
    {
        Book book = new Book();
        book.setDescription("A book about absolutely nothing");
        book.setEAN(Long.valueOf(1284712894));
        book.setPrice(new BigDecimal(200));
        book.setTitle("Blue and the Green!");
        book.setGenre(Genre.TEEN_FICTION);
        //todo image storage

        book.setYear(2018);

        Author author = new Author();
        author.setName("Jake Sorey");
        book.setAuthor(author);

        Set<Pages> pages = new HashSet<>();
        Pages page1 = new Pages();
        page1.setBook(book);
        page1.setChapter("Chapter 1");
        page1.setContent("To do the worst is to do the opposite of the best.");
        page1.setPage(1);
        pages.add(page1);

        book.setPages(pages);

        Publisher publisher = new Publisher();
        publisher.setCity("Cape Town");
        publisher.setCountry("South Africa");
        publisher.setName("Idk WallStreet Journal Incorpreitated.");
        publisher.setDate(LocalDate.now());
        book.setPublisher(publisher);

        List<Book> books = new ArrayList<>(1);
        books.add(book);

        return books;

    }
}
