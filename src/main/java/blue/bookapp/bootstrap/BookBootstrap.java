package blue.bookapp.bootstrap;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.domain.*;
import blue.bookapp.repositories.BookRepository;
import blue.bookapp.services.BookService;
import blue.bookapp.services.ImageService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Profile("h2")
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
        book1.setDescription("Ex-military policeman Jack Reacher is a drifter. " +
                "He's just passing through Margrave, Georgia, and in less than an hour, he's arrested for murder. " +
                "Not much of a welcome. All Jack knows is that he didn't kill anybody. At least not here. Not lately. " +
                "But he doesn't stand a chance of convincing anyone. not in Margrave, Georgia. Not a chance in hell.");
        book1.setEAN(Long.valueOf(1284712894));
        book1.setTitle("Killing Floor");
        book1.setGenre(Genre.THRILLER);

        book1.setYear(2018);

        Author author1 = new Author();
        author1.setName("Lee Child");
        book1.setAuthor(author1);

        Set<Pages> pages1 = new HashSet<>();
        Pages page1 = new Pages();
        page1.setTitle("Chapter 1");
        page1.setContent("I WAS ARRESTED IN ENO’S DINER. AT TWELVE O’CLOCK. I was eating eggs and " +
                "drinking coffee. A late breakfast, not lunch. I was wet and tired after a long " +
                "walk in heavy rain. All the way from the highway to the edge of town. " +
                "The diner was small, but bright and clean. Brand-new, built to resemble a " +
                "converted railroad car. Narrow, with a long lunch counter on one side and a " +
                "kitchen bumped out back. Booths lining the opposite wall. A doorway where " +
                "the center booth would be. " +
                "I was in a booth, at a window, reading somebody’s abandoned newspaper " +
                "about the campaign for a president I didn’t vote for last time and wasn’t going " +
                "to vote for this time. Outside, the rain had stopped but the glass was still " +
                "pebbled with bright drops. I saw the police cruisers pull into the gravel lot. " +
                "They were moving fast and crunched to a stop. Light bars flashing and " +
                "popping. Red and blue light in the raindrops on my window. Doors burst " +
                "open, policemen jumped out. Two from each car, weapons ready. Two " +
                "revolvers, two shotguns. This was heavy stuff. One revolver and one shotgun " +
                "ran to the back. One of each rushed the door. " +
                "I just sat and watched them. I knew who was in the diner. A cook in back. " +
                "Two waitresses. Two old men. And me. This operation was for me. I had been " +
                "in town less than a half hour. The other five had probably been here all their " +
                "lives. Any problem with any of them and an embarrassed sergeant would have " +
                "shuffled in. He would be apologetic. He would mumble to them. He would " +
                "ask them to come down to the station house. So the heavy weapons and the " +
                "rush weren’t for any of them. They were for me. I crammed egg into my " +
                "mouth and trapped a five under the plate. Folded the abandoned newspaper " +
                "into a square and shoved it into my coat pocket. Kept my hands above the " +
                "table and drained my cup. " +
                "The guy with the revolver stayed at the door. He went into a crouch and " +
                "pointed the weapon two-handed. At my head. The guy with the shotgun " +
                "approached close. These were fit lean boys. Neat and tidy. Textbook moves. " +
                "The revolver at the door could cover the room with a degree of accuracy. The " +
                "shotgun up close could splatter me all over the window. The other way around " +
                "would be a mistake. The revolver could miss in a close-quarters struggle and " +
                "a long-range shotgun blast from the door would kill the arresting officer and " +
                "the old guy in the rear booth as well as me. So far, they were doing it right. " +
                "No doubt about that. They had the advantage. No doubt about that, either. The " +
                "tight booth trapped me. I was too hemmed in to do much. I spread my hands ");
        page1.setPage(1);
        page1.setBook(book1);

        Pages page2 = new Pages();
        page2.setTitle("");
        page2.setContent("on the table. The officer with the shotgun came near. " +
                "“Freeze! Police!” he screamed. " +
                "He was screaming as loud as he could. Blowing off his tension and trying " +
                "to scare me. Textbook moves. Plenty of sound and fury to soften the target. I " +
                "raised my hands. The guy with the revolver started in from the door. The guy " +
                "with the shotgun came closer. Too close. Their first error. If I had to, I might " +
                "have lunged for the shotgun barrel and forced it up. A blast into the ceiling " +
                "perhaps and an elbow into the policeman’s face and the shotgun could have " +
                "been mine. The guy with the revolver had narrowed his angle and couldn’t " +
                "risk hitting his partner. It could have ended badly for them. But I just sat " +
                "there, hands raised. The guy with the shotgun was still screaming and " +
                "jumping. " +
                "“Out here on the floor!” he yelled. " +
                "I slid slowly out of the booth and extended my wrists to the officer with the " +
                "revolver. I wasn’t going to lie on the floor. Not for these country boys. Not if " +
                "they brought along their whole police department with howitzers. " +
                "The guy with the revolver was a sergeant. He was pretty calm. The shotgun " +
                "covered me as the sergeant holstered his revolver and unclipped the handcuffs " +
                "from his belt and clicked them on my wrists. The backup team came in " +
                "through the kitchen. They walked around the lunch counter. Took up position " +
                "behind me. They patted me down. Very thorough. I saw the sergeant " +
                "acknowledge the shakes of the heads. No weapon. " +
                "The backup guys each took an elbow. The shotgun still covered me. The " +
                "sergeant stepped up in front. He was a compact, athletic white man. Lean and " +
                "tanned. My age. The acetate nameplate above his shirt pocket said: Baker. He " +
                "looked up at me. " +
                "“You are under arrest for murder,” he said. “You have the right to remain " +
                "silent. Anything you say may be used as evidence against you. You have the " +
                "right to representation by an attorney. Should you be unable to afford an " +
                "attorney, one will be appointed for you by the State of Georgia free of charge. " +
                "Do you understand these rights?” " +
                "It was a fine rendition of Miranda. He spoke clearly. He didn’t read it from " +
                "a card. He spoke like he knew what it meant and why it was important. To " +
                "him and to me. I didn’t respond. " +
                "“Do you understand your rights?” he said again. " +
                "Again I didn’t respond. Long experience had taught me that absolute");
        page2.setPage(2);
        page2.setBook(book1);

        Pages page3 = new Pages();
        page3.setTitle("");
        page3.setContent("silence is the best way. Say something, and it can be misheard. " +
                "Misunderstood. Misinterpreted. It can get you convicted. It can get you killed. " +
                "Silence upsets the arresting officer. He has to tell you silence is your right but " +
                "he hates it if you exercise that right. I was being arrested for murder. But I " +
                "said nothing. " +
                "“Do you understand your rights?” the guy called Baker asked me again. " +
                "“Do you speak English?” " +
                "He was calm. I said nothing. He remained calm. He had the calm of a man " +
                "whose moment of danger had passed. He would just drive me to the station " +
                "house and then I would become someone else’s problem. He glanced round " +
                "his three fellow officers. " +
                "“OK, make a note, he’s said nothing,” he grunted. “Let’s go.” " +
                "I was walked toward the door. At the door we formed a single file. First " +
                "Baker. Then the guy with the shotgun, walking backward, still with the big " +
                "black barrel pointing at me. His nameplate said: Stevenson. He too was a " +
                "medium white man in good shape. His weapon looked like a drainpipe. " +
                "Pointing at my gut. Behind me were the backup guys. I was pushed through " +
                "the door with a hand flat on my back. " +
                "Outside in the gravel lot the heat was up. It must have rained all night and " +
                "most of the morning. Now the sun was blasting away and the ground was " +
                "steaming. Normally this would be a dusty hot place. Today it was steaming " +
                "with that wonderful heady aroma of drenched pavement under a hot noon sun. " +
                "I stood face up to the sun and inhaled as the officers regrouped. One at each " +
                "elbow for the short walk to the cars. Stevenson still on the ball with the pumpaction. " +
                "At the first car he skipped backward a step as Baker opened the rear " +
                "door. My head was pushed down. I was nudged into the car with a neat hip-tohip " +
                "contact from the left-hand backup. Good moves. In a town this far from " +
                "anywhere, surely the result of a lot of training rather than a lot of experience. " +
                "I was alone in the back of the car. A thick glass partition divided the space. " +
                "The front doors were still open. Baker and Stevenson got in. Baker drove. " +
                "Stevenson was twisted around keeping me under observation. Nobody talked. " +
                "The backup car followed. The cars were new. Quiet and smooth riding. Clean " +
                "and cool inside. No ingrained traces of desperate and pathetic people riding " +
                "where I was riding. " +
                "I looked out of the window. Georgia. I saw rich land. Heavy, damp red " +
                "earth. Very long and straight rows of low-bushes in the fields. Peanuts, " +
                "maybe. Belly crops, but valuable to the grower. Or to the owner. Did people ");
        page3.setPage(3);
        page3.setBook(book1);

        Pages page4 = new Pages();
        page4.setTitle("");
        page4.setContent("own their land here? Or did giant corporations? I didn’t know. " +
                "The drive to town was short. The car hissed over the smooth soaked " +
                "tarmac. After maybe a half mile I saw two neat buildings, both new, both with " +
                "tidy landscaping. The police station and the firehouse. They stood alone " +
                "together, behind a wide lawn with a statue, north edge of town. Attractive " +
                "county architecture on a generous budget. Roads were smooth tarmac, " +
                "sidewalks were red blocks. Three hundred yards south, I could see a blinding " +
                "white church steeple behind a small huddle of buildings. I could see flagpoles, " +
                "awnings, crisp paint, green lawns. Everything refreshed by the heavy rain." +
                "Now steaming and somehow intense in the heat. A prosperous community. " +
                "Built, I guessed, on prosperous farm incomes and high taxes on the " +
                "commuters who worked up in Atlanta. " +
                "Stevenson still stared at me as the car slowed to yaw into the approach to " +
                "the station house. A wide semicircle of driveway. I read on a low masonry " +
                "sign: Margrave Police Headquarters. I thought: should I be worried? I was " +
                "under arrest. In a town where I’d never been before. Apparently for murder. " +
                "But I knew two things. First, they couldn’t prove something had happened if " +
                "it hadn’t happened. And second, I hadn’t killed anybody. " +
                "Not in their town, and not for a long time, anyway");
        page4.setPage(4);
        page4.setBook(book1);

        Pages page5 = new Pages();
        page5.setTitle("Chapter 2");
        page5.setContent("WE PULLED UP AT THE DOORS OF THE LONG LOW BUILDING. Baker got out of the " +
                "car and looked up and down along the frontage. The backup guys stood by. " +
                "Stevenson walked around the back of our car. Took up a position opposite " +
                "Baker. Pointed the shotgun at me. This was a good team. Baker opened my " +
                "door. " +
                "“OK, let’s go, let’s go,” he said. Almost a whisper. " +
                "He was bouncing on the balls of his feet, scanning the area. I pivoted " +
                "slowly and twisted out of the car. The handcuffs didn’t help. Even hotter now. " +
                "I stepped forward and waited. The backup fell in behind me. Ahead of me " +
                "was the station house entrance. There was a long marble lintel crisply " +
                "engraved: Town of Margrave Police Headquarters. Below it were plate-glass " +
                "doors. Baker pulled one open. It sucked against rubber seals. The backup " +
                "pushed me through. The door sucked shut behind me. " +
                "Inside it was cool again. Everything was white and chrome. Lights were " +
                "fluorescent. It looked like a bank or an insurance office. There was carpet. A " +
                "desk sergeant stood behind a long reception counter. The way the place " +
                "looked, he should have said: how may I help you, sir? But he said nothing. He " +
                "just looked at me. Behind him was a huge open-plan space. A dark-haired " +
                "woman in uniform was sitting at a wide, low desk. She had been doing " +
                "paperwork on a keyboard. Now she was looking at me. I stood there, an " +
                "officer on each elbow. Stevenson was backed up against the reception counter. " +
                "His shotgun was pointed at me. Baker stood there, looking at me. The desk " +
                "sergeant and the woman in uniform were looking at me. I looked back at " +
                "them. " +
                "Then I was walked to the left. They stopped me in front of a door. Baker " +
                "swung it open and I was pushed into a room. It was an interview facility. No " +
                "windows. A white table and three chairs. Carpet. In the top corner of the " +
                "room, a camera. The air in the room was set very cold. I was still wet from the " +
                "rain. " +
                "I stood there and Baker ferreted into every pocket. My belongings made a " +
                "small pile on the table. A roll of cash. Some coins. Receipts, tickets, scraps. " +
                "Baker checked the newspaper and left it in my pocket. Glanced at my watch " +
                "and left it on my wrist. He wasn’t interested in those things. Everything else " +
                "was swept into a large Ziploc bag. A bag made for people with more in their " +
                "pockets than I carry. The bag had a white panel printed on it. Stevenson wrote " +
                "some kind of a number on the panel.");
        page5.setPage(5);
        page5.setBook(book1);

        pages1.add(page1);
        pages1.add(page2);
        pages1.add(page3);
        pages1.add(page4);
        pages1.add(page5);
        book1.setPages(pages1);

        Publisher publisher1 = new Publisher();
        publisher1.setCountry("United Kingdom");
        publisher1.setName("Putnam");
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

        Book book3 = new Book();
        book3.setTitle("My Past Life");
        book3.setDescription("This is a short fiction about a young man who gets shot on Christmas Eve and goes to heaven. Then he is given a second chance and he is reborn. Read to find out more!");
        book3.setGenre(Genre.SHORT_STORY);
        book3.setEAN(Long.valueOf(9385202));
        book3.setYear(2018);

        Author author3 = new Author();
        author3.setName("Alina Udrea");
        author3.setAge(35);
        book3.setAuthor(author3);

        Set<Pages> pages3 = new HashSet<>();
        Pages page3_1 = new Pages();
        page3_1.setTitle("Chapter 1");
        page3_1.setContent("Dave was hurrying home to his future wife. He would " +
                "propose tonight. He checked the small bump on his chest to " +
                "make sure the tiny box containing the ring was there. He had " +
                "just bought it. He had paid all his savings to buy her the most " +
                "beautiful ring he could afford. Yep, that was it, the most " +
                "beautiful ring he could ever afford, not the most beautiful ring " +
                "there was in store. But he hoped it would be good enough for " +
                "her. The love of his life, Jasmin.They had been together for " +
                "three years and he felt it was the right time to propose. " +
                "Tonight, on Christmas Eve, he would do it.Aftertheir annoying " +
                "relatives would leave, when he would have her for himself, he " +
                "would kneel before her and pop the question. It would be " +
                "perfect. He would first make her think he didn’t get her " +
                "anything this Christmas and then he would propose to her and " +
                "put the ring on her finger. " +
                " Dave could almost see it, how she would cry with joy and " +
                "hug him and everything would be perfect. And by this time next " +
                "year she would be Mrs. Rogers. Jasmin Rogers. Yup. Perfect. His " +
                "heart was beating so fast that he had to focus to calm down. He " +
                "was a strong man, but his heart was racing in anticipation. He " +
                "searched his pockets for his car keys as he was slowly walking ");
        page3_1.setPage(1);
        page3_1.setBook(book3);

        Pages page3_2 = new Pages();
        page3_2.setTitle("");
        page3_2.setContent("down a dark alley as it was the only place where he had found a " +
                "parking spot for his car.There were street lights everywhere but " +
                "the ones on this alley were flickering until they stopped doing " +
                "even that. Just a few more meters and he would start his car " +
                "and go home. " +
                "Finally, he found his car keys in the back of his right pocketof " +
                "his blue-jeans.Then things took a turn for the worse. Everything " +
                "he had dreamed of, everything he had hoped for, all his life " +
                "raced in his head in jumbled blurry images when he felt the " +
                "cold steel of a gun pointed at him and a hoarse male voice " +
                "telling him to give him his wallet. Somehow, he knew this " +
                "wouldn’t end well. He just couldn’t have that perfect life he had " +
                "dreamed of, could he? " +
                "- Wait, please, it's Christmas Eve. I just want to get home to " +
                "my future wife, Dave said with a pleading voice. " +
                "- Give me your wallet and your phone or you won't be " +
                "getting anywhere, got that? Came the answer and the man " +
                "nudged him towards his car, putting the gun to his head " +
                "now. " +
                "- And don't turn around, hurry up!, the man went on, " +
                "nervous now and waiting for him to give him his money. " +
                "- I can give you my wallet, but it's empty! And so are my " +
                "credit cards! " +
                "- Damn! I don't believe you, just give it to me, give me your " +
                "damned wallet man if you want to live for Christmas!");
        page3_2.setPage(2);
        page3_2.setBook(book3);

        Pages page3_3 = new Pages();
        page3_3.setTitle("");
        page3_3.setContent("Here, take it, and Dave gave him his empty wallet. Even if " +
                "he had had money, he would have given him. It wasn't " +
                "worth to die for coins. And he treasured his life. Since he " +
                "met Jasmin three years ago, his life meant something. It " +
                "was worth living and it was good to be him only for having " +
                "met her. " +
                " The man grabbed his wallet and when he saw that it " +
                "was indeed empty, he spit on it and threw it on the wet " +
                "pavement. Then Dave turned around but he could only see " +
                "the man's dirty clothes and a glimpse of his face. Then it all " +
                "went dark. " +
                "- Damn, why did you have to turn? I am not going back to " +
                "jail!! Then he shot him in his head and ran away. The bullet " +
                "went through his leftarcade, just a little above his eye. " +
                " As he was falling to the pavement, Dave saw him ditch " +
                "the gun in a trash can next to an empty diner. Then " +
                "everything went black. " +
                "He didn’t know how much time had passed, but when he " +
                "woke up he was blinded bya white light that at first he " +
                "couldn’t see anything. He felt disoriented and dizzy and at " +
                "first he couldn’t remember anything. Then little by little he " +
                "began to distinguish and to remember. He remembered " +
                "everything, how he was eager to get home to Jasmin, how " +
                "he was attacked and killed. Shot to death. But then where " +
                "was he? Was he still alive? " +
                " He took a few steps towards an elderly woman who " +
                "was coming his way. He looked around and he didn’t ");
        page3_3.setPage(3);
        page3_3.setBook(book3);

        Pages page3_4 = new Pages();
        page3_4.setTitle("");
        page3_4.setContent("recognize the place. When he was next to her, he stopped " +
                "and cleared his throat to make her look at him. " +
                "- Hi, excuse me, do you know where we are? " +
                " She just smiled at him and went on without answering. " +
                "He thought it was odd but he didn’t want to panic even " +
                "more so he decided to ask someone else. He looked " +
                "around one more time but no one was coming his way. His " +
                "head hurt and he was sure he had been shot in his head. " +
                "But why wasn’t he in the hospital? And where was the " +
                "wound? He touched his head where the wound should " +
                "have been but there was nothing there. Then it dawned on " +
                "him: he was in Heaven!! Which meant he was dead!! " +
                "- Damn! Oops, sorry for the language, he immediately " +
                "thought! This sucks! Is this heaven? God, where are you? " +
                "Why did I have to die on Christmas Eve? I was going to " +
                "propose to Jasmin tonight! We were supposed to get " +
                "married next year and now nothing will be the way I " +
                "imagined! Why?? And he began to cry like a helpless baby " +
                "thinking of everything that could have been but wouldn’t " +
                "be, of the life he could have lived with Jasmin, of the " +
                "children they could have had. " +
                " He sat on a bench and tried to recollect himself. " +
                "There was no point in crying over spilled milk. He would " +
                "have to find God and see if there was anything he could do " +
                "to help him. Or at least tell him why he let him die and did " +
                "not save him. But which way to go? ");
        page3_4.setPage(4);
        page3_4.setBook(book3);

        Pages page3_5 = new Pages();
        page3_5.setTitle("");
        page3_5.setContent("He wiped his tears and waited on the bench till he saw " +
                "someone coming. It was a little girl. As she got near him, " +
                "he saw that she was limping. He felt sorry for her and " +
                "thought that maybe she could tell him something about " +
                "this place. " +
                "- Hey little girl, he shyly said, could you tell me what is this " +
                "place? Is this Heaven? Because I can't see any other " +
                "explanation. I mean someone just shot me and then next " +
                "thing I know is that I got to this place. " +
                "- It must be. I was crossing the street when a car hit me. " +
                "Mom was there too but she must have survived. Because I " +
                "can’t find her here. I am scared. " +
                "- That makes two of us. Come, let's go this way and find " +
                "God. He must have a plan for us. " +
                "- By the way, my name is Amy, the little girl said with sorrow " +
                "in her voice. " +
                "- I'm Dave, he simply answered and smiled at her.");
        page3_5.setPage(5);
        page3_5.setBook(book3);

        Pages page3_6 = new Pages();
        page3_6.setTitle("Chapter 2");
        page3_6.setContent("Dave and Amy, the little girl, went silently side by side in search " +
                "of God.All this time he was thinking that she was too young too " +
                "die. But even so, God had taken her to him. Finally, he had to " +
                "ask her: " +
                "- Amy, are you upset that you…. " +
                "- Died? She ended his sentence. No, my parents have always " +
                "taught me to believe in God and that He knows best what " +
                "he does and why he does it. If he called me to him, then I " +
                "know it's for the best. " +
                "- How? Dave asked failing to understand her judgment. " +
                "- Who knows? Maybe if I had lived, I would have developed " +
                "some bad illness and suffered even more. I don’t know. " +
                "And I don’t even want to think about it. " +
                "- Wow, you are pretty smart for your age. But, Dave went " +
                "on, I see the sorrow on your face. You are sad! " +
                "- Yes, but not for me! I have no idea what happened to my " +
                "mom. We were crossing the street when that car hit us. I " +
                "don’t know if she is hurt bad. And she must be really sad " +
                "for me. " +
                "- Oh…. Was all Dave could say. Then they were both silent " +
                "again for a while.");
        page3_6.setPage(6);
        page3_6.setBook(book3);

        pages3.add(page3_1);
        pages3.add(page3_2);
        pages3.add(page3_3);
        pages3.add(page3_4);
        pages3.add(page3_5);
        pages3.add(page3_6);

        book3.setPages(pages3);

        Book book4 = new Book();
        book4.setTitle("In a Moment");
        book4.setDescription("Lindy Gordon is living the enviable life of an entrepreneur in San Francisco. " +
                "Her public relations firm is thriving, her marriage is strong and her apartment at the top of Noe Valley is the envy of her friends. " +
                "Then the unthinkable happens. " +
                "An accident briefly ends her life, leaving her with a scar, cracked bones and fractured images that make no sense - at first. " +
                "As her body heals, she learns little in her past was what she believed it to be. " +
                "Relationships have soured, love and loyalty has been misplaced and the ultimate betrayal has devastating consequences to her financial life. " +
                "With nothing more than wits and long-dormant faith to sustain her, Lindy works to save friendships, renew family ties and open her mind and heart to one who could truly love her.");
        book4.setGenre(Genre.DRAMA);
        book4.setEAN(Long.valueOf(1231545648));
        book4.setYear(2018);

        Author author4 = new Author();
        author4.setName("Sarah Gerdes");
        author4.setAge(0);
        book4.setAuthor(author4);

        Publisher publisher4 = new Publisher();
        publisher4.setName("RPM Publishing");
        publisher4.setCountry("Washington");
        publisher4.setCity("Seattle");
        book4.setPublisher(publisher4);

        Set<Pages> pages4 = new HashSet<>();
        Pages page4_1 = new Pages();
        page4_1.setTitle("PREFACE");
        page4_1.setContent("“Come on, Lindy, come on!” a voice commanded. “You can do it. Breathe! You can start again. " +
                "I know you can!” I felt nothing as I hovered above, watching the doctor tape a device around my mouth, the cord stretching to a machine. " +
                "The upside of having my spirit separated from my body was freedom from the pain I’d experienced during the accident.  " +
                "My hand received a squeeze and I turned to my Grandfather who had been beside me since my death, moments before. " +
                "“Now I’m not so sure I want to return,” I admitted. My body was jolting, blood was on my forehead and cheek, my ribs were black. " +
                "Lines and pricks marked locations where glass had hit and embedded themselves in my skin.  " +
                "But that was nothing compared to the images I’d seen. The quick, staccato spurts of disjointed visuals scared me more than a damaged body. " +
                "A woman leaning on her side on a gurney, frozen with grief. Another being held down against her will, her back to me. " +
                "A man, sitting in an elegant office, torn apart inside but pretending to be in control. " +
                "A heavy-set woman behind a wall of glass, on the phone, counseling someone in a low, concerned voice. " +
                "Me, walking beside a man on a beach, the sunset fading as the moon rose on the horizon. " +
                "The faces were blurred, but the feelings were acutely clear; grief, heartache, pain, but also compassion, joy and love. " +
                "The soft, loose skin on Grandfather’s cheeks dropped into his lips as his eyes glimmered.  " +
                "“That’s a common feeling when it comes time to reenter the body,” he said as though I’d be comforted. " +
                "I wasn’t, and he seemed to know this. “I ");
        page4_1.setPage(1);
        page4_1.setBook(book4);

        Pages pages4_2 = new Pages();
        pages4_2.setTitle("Sarah Gerdes");
        pages4_2.setContent("can tell you that your physical self will be just fine.”  " +
                "“But the inside? My emotions and those who I saw?” He smiled, the hand that was holding mine started to release.  " +
                "“Come on Lindy!” urged the doctor with such force I looked down at him. " +
                "A flood of information passed to me from the doctor who feverishly worked on my motionless body. " +
                "I could sense him using all his known techniques and skills in this moment to bring me back from the dead. " +
                "He was fighting for my life in a way I though was admirable.  " +
                "As if sensing my mood, Grandfather squeezed my hand again, his gaze firm and endowed with a faith far greater than what I possessed. " +
                "“You are necessary. It is part of why you’ve made your choice.” " +
                "I wanted to protest that I wasn’t strong enough to endure what I saw before me, but didn’t have the chance. " +
                "The warmth of my Grandfather’s hand slipped away with his image. " +
                "That instant, the doctor shouted and I felt a coldness followed by excruciating pain; a million nerve endings manually reconnecting. " +
                "“That’s it! We have her heartbeat,” the doctor said triumphantly. I was lying on the operating room table, looking up at a silhouette of my Grandfather’s face. " +
                "“Be patient and trust your inner promptings,” he said faintly. “The noise of your life will try to down them out, but listen,” he emphasized. " +
                "“And Lindy. You will find love again, I promise.” " +
                "As I strained and tried to cry out for him not to leave, another face came into focus; a man with white hair and green eyes. " +
                "“That’s right. You’re getting stronger by the second. In, out, in, out.” He kept coaching me, drawing a hand over my eyes as I tried to open them. " +
                "“Don’t try to look now. You will see soon enough.” " +
                "Pain raced through my body and I heard myself choke, then scream with agony, the point where my eternal self made peace with my physical shell. " +
                "Gradually, my aching eased into an exhausted, but stable state, the words of my Grandfather echoing in my mind.");
        pages4_2.setPage(2);
        pages4_2.setBook(book4);

        Pages page4_3 = new Pages();
        page4_3.setTitle("In A Moment");
        page4_3.setContent("“You’re going to make it, Lindy,” the doctor said confidently, as if he knew what I was going through. " +
                "“You’re right here with us, and you aren’t leaving.” " +
                "I was returning to a world unlike the one I left, and I had asked for it. " +
                "He said I’d find love again. " +
                "In my haze of pain, I wondered why would I need to find something I already had?");
        page4_3.setPage(3);
        page4_3.setBook(book4);

        Pages page4_4 = new Pages();
        page4_4.setTitle("Chapter 1");
        page4_4.setContent("“Well, Lindy Gordon, are you ready to hear what happened?” It was my doctor, Jake Redding, the one who’d been on call during my accident. " +
                "I nodded, trying to focus on his face. " +
                "I wondered if his insistence at using my first and last name was to keep reminding me of who I was. " +
                "“Please call me Lindy,” I said, my voice rough, hoping to reassure him—and myself. " +
                "“Of course,” he answered politely. “Focus on me so I can watch your eye movement as I speak." +
                "” I did. The doctor had smooth skin and linen white hair, coarse and thick on the top and the sides, like he’d forgotten his last appointment with the hair dresser.  " +
                "“To start, a car struck the cab you were in and your head smashed the window. " +
                "Do you remember that?”  “Not the hit,” I answered slowly, starting to move my head.  " +
                "“Don’t,” he cautioned, but his advice came too late. " +
                "A spike shot from my shoulder blade to my temple. " +
                "“When you came in, you were in pretty bad shape and we wanted to prevent you from going into cardiac arrest. " +
                "We put you in a blue suit to cool you down, like an ice pack. You had it on from head to toe.” " +
                "“You refrigerated me?” I asked. Dr. Redding chuckled. “In a manner of speaking, yes. " +
                "No one knew how long your brain had been without oxygen, so we attempted to cool it down. " +
                "It’s quite evident you have one that still works.” A functioning brain was good. " +
                "A working body was better. In a panicked rush, I twitched my fingers, then my toes. " +
                "I inhaled to speak, and caught myself.  ");
        page4_4.setPage(4);
        page4_4.setBook(book4);

        pages4.add(page4_1);
        pages4.add(pages4_2);
        pages4.add(page4_3);
        pages4.add(page4_4);
        book4.setPages(pages4);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        return books;
    }

    private void saveImages() throws IOException
    {
        Set<BookCommand> books = bookService.listBooks();
        MultipartFile multipartFile1 = new MockMultipartFile("book1.jpg", new FileInputStream(new File(".bootstrapBookImages/book1.jpg")));
        MultipartFile multipartFile2 = new MockMultipartFile("book2.jpg", new FileInputStream(new File(".bootstrapBookImages/book2.jpg")));
        MultipartFile multipartFile3 = new MockMultipartFile("book3.jpg", new FileInputStream(new File(".bootstrapBookImages/book3.jpg")));
        MultipartFile multipartFile4 = new MockMultipartFile("book4.jpg", new FileInputStream(new File(".bootstrapBookImages/book4.jpg")));
                books.iterator().forEachRemaining(book ->
        {
            if (book.getId() == 1)
                imageService.saveImageFile(book.getId(), multipartFile1);
            if (book.getId() == 2)
                imageService.saveImageFile(book.getId(), multipartFile2);
            if (book.getId() == 3)
                imageService.saveImageFile(book.getId(), multipartFile3);
            if (book.getId() == 4)
                imageService.saveImageFile(book.getId(), multipartFile4);
        });
    }
}
