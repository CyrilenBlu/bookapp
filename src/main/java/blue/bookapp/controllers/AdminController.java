package blue.bookapp.controllers;

import blue.bookapp.commands.BookCommand;
import blue.bookapp.domain.Admin;
import blue.bookapp.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private AdminService adminService;
    private BookService bookService;
    private AuthorService authorService;
    private PublisherService publisherService;
    private PagesService pagesService;
    private Admin loggedAdmin = new Admin();

    public AdminController(AdminService adminService, BookService bookService, AuthorService authorService, PublisherService publisherService, PagesService pagesService) {
        this.adminService = adminService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.pagesService = pagesService;
    }

    @GetMapping({"/admin-cpl", "/error"})
    public String adminNav(Model model)
    {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "admin/admin-cpl";
    }


    @GetMapping("/admin-control")
    public String successLogin(Model model)
    {
        model.addAttribute("admins", adminService.getAdmins());
        model.addAttribute("admin", loggedAdmin);
        return "admin/control/home";
    }

    @GetMapping("/check")
    public String checkDetails(@ModelAttribute Admin admin)
    {
        boolean checkUsername = adminService.checkUser(admin.getUsername());
        boolean checkPassword = adminService.checkPass(admin.getPassword());

        if (checkPassword && checkUsername)
        {
            loggedAdmin = admin;
            loggedAdmin.setCheckLogged(true);
            return "redirect:/admin-control";
        } else {
            loggedAdmin.setCheckLogged(false);
            return "admin/admin-cpl";
        }
    }

    @GetMapping("/logout")
    public String logout()
    {
        loggedAdmin.setPassword(null);
        loggedAdmin.setUsername(null);
        loggedAdmin.setCheckLogged(false);
        return "redirect:/home";
    }

    @GetMapping("/admin-book-list")
    public String listBooks_Admin(Model model)
    {
        model.addAttribute("books", bookService.listBooks());
        return "admin/control/book-list";
    }

    @GetMapping("/book/{id}/show")
    public String viewBook_Admin(@PathVariable String id, Model model)
    {
        model.addAttribute("book", bookService.bookInfoById(Long.valueOf(id)));
        return "admin/control/book-show";
    }

    @GetMapping("/book/{id}/update")
    public String updateBook_Admin(@PathVariable String id, Model model)
    {
        model.addAttribute("book", bookService.bookInfoById(Long.valueOf(id)));
        model.addAttribute("pages", pagesService.listPagesByBookId(Long.valueOf(id)));
        return "admin/control/book-update";
    }

    @PostMapping("book")
    public String updateBook(@ModelAttribute BookCommand bookCommand)
    {
        BookCommand savedCommand = bookService.updateBook(bookCommand);
        return "redirect:/book/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/book/{id}/pages")
    public String pagesBook_Admin(@PathVariable String id, Model model)
    {
        model.addAttribute("pages", pagesService.listPagesByBookId(Long.valueOf(id)));
        return "admin/control/book-pages";
    }

    @GetMapping("/admin-author-list")
    public String listAuthors_Admin(Model model)
    {
        model.addAttribute("authors", authorService.findAll());
        return "admin/control/author-list";
    }

    @GetMapping("/admin-publisher-list")
    public String listPublishers_Admin(Model model)
    {
        model.addAttribute("publishers", publisherService.findAll());
        return "admin/control/publisher-list";
    }



    @GetMapping("book/{id}/delete")
    public String deleteBook(@PathVariable String id)
    {
        if (loggedAdmin.isCheckLogged())
        {
            bookService.removeBookById(Long.valueOf(id));
        }
        return "redirect:/admin-book-list";
    }
}
