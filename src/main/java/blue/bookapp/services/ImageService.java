package blue.bookapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long bookId, MultipartFile file);
}
