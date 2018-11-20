package blue.bookapp.controllers;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.services.BookService;
import blue.bookapp.services.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private BookService bookService;
    private ImageService imageService;

    public ImageController(BookService bookService, ImageService imageService) {
        this.bookService = bookService;
        this.imageService = imageService;
    }

    @GetMapping("book/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model)
    {
        model.addAttribute("book", bookService.findCommandById(Long.valueOf(id)));
        return "admin/book/imageuploadform";
    }

    @PostMapping("book/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile multipartFile)
    {
        try {
            System.out.println(multipartFile.getBytes().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageService.saveImageFile(Long.valueOf(id), multipartFile);
        return "redirect:/book/" + id + "/show";
    }



    @GetMapping("book/{id}/bookimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException
    {
        BookCommand bookCommand = bookService.findCommandById(Long.valueOf(id));

        if (bookCommand.getImage() != null)
        {
            byte[] bytes = new byte[bookCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : bookCommand.getImage())
            {
                bytes[i++] = wrappedByte;
            }

            response.setContentType("image/jpeg");
            InputStream inputStream = new ByteArrayInputStream(bytes);
            IOUtils.copy(inputStream, response.getOutputStream());
        }
    }
}
