package blue.bookapp.services;

import blue.bookapp.domain.Book;
import blue.bookapp.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService{

    private final BookRepository bookRepository;

    public ImageServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long bookId, MultipartFile file) {
        try{
            Book book = bookRepository.findById(bookId).get();

            Byte[] bytes = new Byte[file.getBytes().length];
            int i = 0;

            for (byte b : file.getBytes())
            {
                bytes[i++] = b;
            }

            book.setImage(bytes);
            bookRepository.save(book);

        } catch (IOException e)
        {
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}
