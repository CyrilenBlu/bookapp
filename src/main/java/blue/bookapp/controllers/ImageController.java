package blue.bookapp.controllers;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.services.BookService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    BookService bookService;

    public ImageController(BookService bookService) {
        this.bookService = bookService;
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
