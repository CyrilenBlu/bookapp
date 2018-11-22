package blue.bookapp.controllers;

import blue.bookapp.services.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendemail")
    public ModelAndView receiveSendEmail(@RequestParam("name") String name, @RequestParam("message") String message,
                                         RedirectAttributes redirectAttributes){
        emailService.sendMessage("iexistlol123@gmail.com","Book App Mail","Name: " + name +"\n\nMessage: " + message);
        redirectAttributes.addFlashAttribute("message", "Email Sent!");
        return new ModelAndView(new RedirectView("/contact"));
    }


}
