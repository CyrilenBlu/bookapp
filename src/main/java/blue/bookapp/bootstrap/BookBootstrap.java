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
        List<Book> books = new ArrayList<>(2);

        Book book1 = new Book();
        book1.setDescription("A book about absolutely nothing");
        book1.setEAN(Long.valueOf(1284712894));
        book1.setPrice(new BigDecimal(200));
        book1.setTitle("Blue and the Green!");
        book1.setGenre(Genre.TEEN_FICTION);
        //todo image storage

        book1.setYear(2018);

        Author author1 = new Author();
        author1.setName("Jake Sorey");
        book1.setAuthor(author1);

        Set<Pages> pages1 = new HashSet<>();
        Pages page1 = new Pages();
        page1.setChapter("Chapter 1");
        page1.setContent("To do the worst is to do the opposite of the best.");
        page1.setPage(1);
        page1.setBook(book1);

        pages1.add(page1);
        book1.setPages(pages1);

        Publisher publisher1 = new Publisher();
        publisher1.setCity("Cape Town");
        publisher1.setCountry("South Africa");
        publisher1.setName("Idk WallStreet Journal Incorpreitated.");
        publisher1.setDate(LocalDate.now());
        book1.setPublisher(publisher1);



        Book book2 = new Book();
        book2.setTitle("My life as a sushi investor.");
        book2.setDescription("I really don't have nothing else to do with me laifu.");
        book2.setGenre(Genre.COMEDY);
        book2.setEAN(Long.valueOf(1231545648));
        book2.setPrice(new BigDecimal(300));
        book2.setYear(2016);

        Author author2 = new Author();
        author2.setName("Jeff Wick");
        book2.setAuthor(author2);

        Publisher publisher2 = new Publisher();
        publisher2.setCity("England");
        publisher2.setCountry("United Kingdom");
        publisher2.setName("England is my City");
        publisher2.setDate(LocalDate.now());
        book2.setPublisher(publisher2);

        Set<Pages> pages2 = new HashSet<>();
        Pages page2_1 = new Pages();
        page2_1.setChapter("Chapter 1");
        page2_1.setContent("Hello world he said!");
        page2_1.setPage(1);
        page2_1.setBook(book2);


        Pages pages2_2 = new Pages();
        pages2_2.setChapter("Chapter 1");
        pages2_2.setContent("Hello world pt.2");
        pages2_2.setPage(2);
        pages2_2.setBook(book2);

        pages2.add(page2_1);
        pages2.add(pages2_2);
        book2.setPages(pages2);


        books.add(book1);
        books.add(book2);

        return books;

    }
}
