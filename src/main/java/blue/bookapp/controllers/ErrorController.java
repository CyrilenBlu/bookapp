package blue.bookapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError()
    {
        return "redirect:/home";
    }

    @Override
    public String getErrorPath() {
        return "redirect:/home";
    }
}
