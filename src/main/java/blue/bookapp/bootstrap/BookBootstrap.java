package blue.bookapp.bootstrap;

import blue.bookapp.domain.*;
import blue.bookapp.repositories.BookRepository;
import blue.bookapp.services.BookService;
import blue.bookapp.services.ImageService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BookBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private BookRepository bookRepository;
    private BookService bookService;
    private ImageService imageService;


    public BookBootstrap(BookRepository bookRepository, BookService bookService, ImageService imageService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.imageService = imageService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        bookRepository.saveAll(getBooks());
        try {
            saveImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(bookService.listBooksByAuthorName("test"));
        //System.out.println(bookService.listBooksByAuthorName("Jake Sorey"));
    }

    private List<Book> getBooks()
    {
        List<Book> books = new ArrayList<>(2);

        Book book1 = new Book();
        book1.setDescription("A book about absolutely nothing");
        book1.setEAN(Long.valueOf(1284712894));
        book1.setTitle("Killing Floor");
        book1.setGenre(Genre.TEEN_FICTION);
        //todo image storage

        book1.setYear(2018);

        Author author1 = new Author();
        author1.setName("Lee Child");
        book1.setAuthor(author1);

        Set<Pages> pages1 = new HashSet<>();
        Pages page1 = new Pages();
        page1.setTitle("Chapter 1");
        page1.setContent("I WAS ARRESTED IN ENO’S DINER. AT TWELVE O’CLOCK. I was eating eggs and drinking coffee. " +
                "A late breakfast, not lunch. I was wet and tired after a long walk in heavy rain. All the way from the highway to the edge of town.\n" +
                "The diner was small, but bright and clean. Brand-new, built to resemble a converted railroad car. " +
                "Narrow, with a long lunch counter on one side and a kitchen bumped out back. Booths lining the opposite wall. " +
                "A doorway where the center booth would be." +
                "I was in a booth, at a window, reading somebody’s abandoned newspaper about the campaign for a president I didn’t vote for last time and wasn’t going to vote for this time. " +
                "Outside, the rain had stopped but the glass was still pebbled with bright drops. " +
                "I saw the police cruisers pull into the gravel lot. " +
                "They were moving fast and crunched to a stop. " +
                "Light bars flashing and popping. Red and blue light in the raindrops on my window. " +
                "Doors burst open, policemen jumped out. Two from each car, weapons ready. " +
                "Two revolvers, two shotguns. " +
                "This was heavy stuff. " +
                "One revolver and one shotgun ran to the back. " +
                "One of each rushed the door." +
                "I just sat and watched them. " +
                "I knew who was in the diner." +
                " A cook in back. Two waitresses. Two old men. And me. This operation was for me. " +
                "I had been in town less than a half hour. The other five had probably been here all their lives. " +
                "Any problem with any of them and an embarrassed sergeant would have shuffled in. He would be apologetic. " +
                "He would mumble to them. He would ask them to come down to the station house. So the heavy weapons and the rush weren’t for any of them. " +
                "They were for me. I crammed egg into my mouth and trapped a five under the plate. " +
                "Folded the abandoned newspaper into a square and shoved it into my coat pocket. " +
                "Kept my hands above the table and drained my cup." +
                "The guy with the revolver stayed at the door. He went into a crouch and pointed the weapon two-handed. At my head. " +
                "The guy with the shotgun approached close. These were fit lean ");
        page1.setPage(1);
        page1.setBook(book1);

        Pages page2 = new Pages();
        page2.setTitle("");
        page2.setContent("boys. Neat and tidy. Textbook moves. The revolver at the door could cover the room with a degree of accuracy. " +
                "The shotgun up close could splatter me all over the window. The other way around would be a mistake. " +
                "The revolver could miss in a close-quarters struggle and a long-range shotgun blast from the door would kill the arresting officer and the old guy in the rear booth as well as me. " +
                "So far, they were doing it right. No doubt about that. They had the advantage. No doubt about that, either. The tight booth trapped me. I was too hemmed in to do much. " +
                "I spread my hands on the table. The officer with the shotgun came near." +
                "“Freeze! Police!” he screamed." +
                "He was screaming as loud as he could. Blowing off his tension and trying to scare me. Textbook moves. " +
                "Plenty of sound and fury to soften the target. I raised my hands. The guy with the revolver started in from the door. " +
                "The guy with the shotgun came closer. Too close. Their first error. If I had to, I might have lunged for the shotgun barrel and forced it up. " +
                "A blast into the ceiling perhaps and an elbow into the policeman’s face and the shotgun could have been mine. " +
                "The guy with the revolver had narrowed his angle and couldn’t risk hitting his partner. It could have ended badly for them. " +
                "But I just sat there, hands raised. The guy with the shotgun was still screaming and jumping." +
                "“Out here on the floor!” he yelled." +
                "I slid slowly out of the booth and extended my wrists to the officer with the revolver. I wasn’t going to lie on the floor. " +
                "Not for these country boys. Not if they brought along their whole police department with howitzers." +
                "The guy with the revolver was a sergeant. He was pretty calm. " +
                "The shotgun covered me as the sergeant holstered his revolver and unclipped the handcuffs from his belt and clicked them on my wrists. " +
                "The backup team came in through the kitchen. They walked around the lunch counter. Took up position behind me. They patted me down. Very thorough. " +
                "I saw the sergeant acknowledge the shakes of the heads. No weapon." +
                "The backup guys each took an elbow. The shotgun still covered me. The sergeant stepped ");
        page2.setPage(2);
        page2.setBook(book1);

        Pages page3 = new Pages();
        page3.setTitle("");
        page3.setContent("up in front. He was a compact, athletic white man. Lean and tanned. My age. " +
                "The acetate nameplate above his shirt pocket said: Baker. He looked up at me." +
                "“You are under arrest for murder,” he said. “You have the right to remain silent. Anything you say may be used as evidence against you. " +
                "You have the right to representation by an attorney. " +
                "Should you be unable to afford an attorney, one will be appointed for you by the State of Georgia free of charge. " +
                "Do you understand these rights?”" +
                "It was a fine rendition of Miranda. He spoke clearly. " +
                "He didn’t read it from a card. He spoke like he knew what it meant and why it was important. To him and to me. " +
                "I didn’t respond." +
                "“Do you understand your rights?” he said again." +
                "Again I didn’t respond. Long experience had taught me that absolute silence is the best way. " +
                "Say something, and it can be misheard. Misunderstood. Misinterpreted. It can get you convicted. " +
                "It can get you killed. Silence upsets the arresting officer. " +
                "He has to tell you silence is your right but he hates it if you exercise that right. " +
                "I was being arrested for murder. But I said nothing." +
                "“Do you understand your rights?” the guy called Baker asked me again. “Do you speak English?”" +
                "He was calm. I said nothing. He remained calm. He had the calm of a man whose moment of danger had passed. " +
                "He would just drive me to the station house and then I would become someone else’s problem. " +
                "He glanced round his three fellow officers." +
                "“OK, make a note, he’s said nothing,” he grunted. “Let’s go.”" +
                "I was walked toward the door. At the door we formed a single file. First Baker. " +
                "Then the guy with the shotgun, walking backward, still with the big black barrel pointing at me. " +
                "His nameplate said: Stevenson. He too was a medium white man in good shape. His weapon looked like a drainpipe. " +
                "Pointing at my gut. Behind me were the backup guys. I was pushed through the door with a hand flat on my back.");
        page3.setPage(3);
        page3.setBook(book1);

        pages1.add(page1);
        pages1.add(page2);
        pages1.add(page3);
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
        page2_1.setTitle("Chapter 1");
        page2_1.setContent("Hello world he said!");
        page2_1.setPage(1);
        page2_1.setBook(book2);


        Pages pages2_2 = new Pages();
        pages2_2.setTitle("Chapter 1");
        pages2_2.setContent("Hello world pt.2");
        pages2_2.setPage(2);
        pages2_2.setBook(book2);

        Pages pages2_3 = new Pages();
        pages2_3.setTitle("Chapter 1");
        pages2_3.setContent("Sushi taste amazin on toast.");
        pages2_3.setPage(3);
        pages2_3.setBook(book2);

        pages2.add(page2_1);
        pages2.add(pages2_2);
        pages2.add(pages2_3);
        book2.setPages(pages2);


        books.add(book1);
        books.add(book2);

        return books;
    }

    private void saveImages() throws IOException
    {
        Set<Book> books = bookService.listBooks();
        MultipartFile multipartFile1 = new MockMultipartFile("book1.jpg", new FileInputStream(new File(".bootstrapBookImages/book1.jpg")));
        MultipartFile multipartFile2 = new MockMultipartFile("book2.jpg", new FileInputStream(new File(".bootstrapBookImages/book2.jpg")));
        books.iterator().forEachRemaining(book ->
        {
            if (book.getId() == 1)
                imageService.saveImageFile(book.getId(), multipartFile1);
            if (book.getId() == 2)
                imageService.saveImageFile(book.getId(), multipartFile2);
        });
    }
}
