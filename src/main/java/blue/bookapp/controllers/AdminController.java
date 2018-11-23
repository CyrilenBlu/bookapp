package blue.bookapp.controllers;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.commands.BookCommand;
import blue.bookapp.commands.PagesCommand;
import blue.bookapp.commands.PublisherCommand;
import blue.bookapp.domain.Admin;
import blue.bookapp.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
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

    @GetMapping({"/admin-cpl"})
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
        if (loggedAdmin.isCheckLogged())
            return "redirect:/admin-book-list";
        return "redirect:/admin-cpl";
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

    //BOOK CONTROL

    @GetMapping("/admin-book-list")
    public String listBooks_Admin(Model model)
    {
        model.addAttribute("books", bookService.listBooks());
        if (loggedAdmin.isCheckLogged())
            return "admin/book/book-list";
        return "redirect:/admin-cpl";
    }

    @GetMapping("/book/{id}/show")
    public String viewBook_Admin(@PathVariable String id, Model model)
    {
        model.addAttribute("book", bookService.bookInfoById(Long.valueOf(id)));
        if (loggedAdmin.isCheckLogged())
            return "admin/book/book-show";
        return "redirect:/admin-cpl";
    }

    @GetMapping("book/{id}/delete")
    public String deleteBook(@PathVariable String id)
    {
        if (loggedAdmin.isCheckLogged())
        {
            bookService.removeBookById(Long.valueOf(id));
            return "redirect:/admin-book-list";
        } else return "redirect:/admin-cpl";

    }

    @GetMapping("/book/{id}/update")
    public String updateBook_Admin(@PathVariable String id, Model model)
    {
        model.addAttribute("book", bookService.bookInfoById(Long.valueOf(id)));
        model.addAttribute("pages", pagesService.listPagesByBookId(Long.valueOf(id)));
        if (loggedAdmin.isCheckLogged())
            return "admin/book/book-update";
        return "redirect:/admin-cpl";
    }

    @PostMapping("book")
    public String updateBook(@ModelAttribute BookCommand bookCommand)
    {
        BookCommand savedCommand = bookService.updateBook(bookCommand);
        return "redirect:/book/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/book/new")
    public String addBook_Admin(Model model)
    {
        model.addAttribute("book", new BookCommand());
        if (loggedAdmin.isCheckLogged())
            return "admin/book/book-new";
        return "redirect:/admin-cpl";
    }

    @PostMapping("book_add")
    public String addBook(@ModelAttribute BookCommand bookCommand)
    {
        BookCommand savedBook = bookService.addBook(bookCommand);
        return "redirect:/admin-book-list";
    }

    @GetMapping("/book/{id}/author_publisher")
    public String changeAuthor_Admin(@PathVariable String id, Model model)
    {
        model.addAttribute("book", bookService.findCommandById(Long.valueOf(id)));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("publishers", publisherService.findAll());
        if (loggedAdmin.isCheckLogged())
            return "admin/book/book-author-publisher";
        return "redirect:/admin-cpl";
    }

    @PostMapping("author_publisher")
    public String updateAuthorPublisher(@ModelAttribute BookCommand bookCommand)
    {
        BookCommand savedBook = bookService.updateAuthorPublisher(bookCommand);
        return "redirect:/admin-book-list";
    }

    @GetMapping("/book/{id}/pages")
    public String pagesBook_Admin(@PathVariable String id, Model model)
    {
        model.addAttribute("pages", pagesService.listPagesByBookId(Long.valueOf(id)));
        model.addAttribute("book", bookService.bookInfoById(Long.valueOf(id)));
        if (loggedAdmin.isCheckLogged())
            return "admin/book/book-pages";
        return "redirect:/admin-cpl";
    }

    @GetMapping("/book/{id}/pages/{pageId}/update")
    public String viewPage_Admin(@PathVariable String id, @PathVariable String pageId, Model model) {
        model.addAttribute("book", bookService.bookInfoById(Long.valueOf(id)));
        model.addAttribute("page", pagesService.getCommandByBookByIdAndPageId(Long.valueOf(id), Long.valueOf(pageId)));
        if (loggedAdmin.isCheckLogged())
            return "admin/book/pages/pages-update";
        return "redirect:/admin-cpl";
    }

    @PostMapping("/book/{id}/page")
    public String updatePage(@ModelAttribute PagesCommand pagesCommand)
    {
        System.out.println(pagesCommand.getBookId() + " pageId: " + pagesCommand.getId());
        PagesCommand savedCommand = pagesService.updatePageCommand(pagesCommand);
        log.debug("Saved Book id: " + savedCommand.getBookId());
        log.debug("Saved page id: " + savedCommand.getId());

        return "redirect:/book/{id}/update";
    }

    @GetMapping("/book/{id}/pages/new")
    public String newPage_Admin(@PathVariable String id, Model model)
    {
        PagesCommand pagesCommand = new PagesCommand();
        pagesCommand.setBookId(Long.valueOf(id));
        model.addAttribute("page", pagesCommand);
        model.addAttribute("book", bookService.findCommandById(Long.valueOf(id)));
        if (loggedAdmin.isCheckLogged())
            return "admin/book/pages/pages-new";
        return "redirect:/admin-cpl";
    }

    @PostMapping("/newpage")
    public String newPage(@ModelAttribute PagesCommand pagesCommand)
    {
        PagesCommand savedPages = pagesService.addCommand(pagesCommand);
        return "redirect:/book/" + pagesCommand.getBookId() + "/pages";
    }

    @GetMapping("/book/{id}/pages/{pageId}/delete")
    public String removePage_Admin(@PathVariable String id, @PathVariable String pageId)
    {
        if (loggedAdmin.isCheckLogged())
        {
            pagesService.removeById(Long.valueOf(id), Long.valueOf(pageId));
        } else return "redirect:/admin-cpl";
        return "redirect:/book/{id}/pages";

    }

    //END OF BOOK CONTROL

    // AUTHOR CONTROL

    @GetMapping("/author/new")
    public String newAuthor_Admin(Model model)
    {
        model.addAttribute("author", new AuthorCommand());
        if (loggedAdmin.isCheckLogged())
            return "admin/author/author-new";
        return "redirect:/admin-cpl";
    }

    @PostMapping("newauthor")
    public String newAuthor(@ModelAttribute AuthorCommand authorCommand)
    {
        AuthorCommand savedCommand = authorService.addAuthor(authorCommand);
        return "redirect:/admin-author-list";
    }

    @GetMapping("/admin-author-list")
    public String listAuthors_Admin(Model model)
    {
        model.addAttribute("authors", authorService.findAll());
        if (loggedAdmin.isCheckLogged())
            return "admin/author/author-list";
        return "redirect:/admin-cpl";
    }

    @GetMapping("/author/{id}/delete")
    public String deleteAuthor_Admin(@PathVariable String id)
    {
        if (loggedAdmin.isCheckLogged()) {
            authorService.removeById(Long.valueOf(id));
            return "redirect:/admin-author-list";
        }
        return "redirect:/admin-cpl";
    }

    @GetMapping("/author/{id}/update")
    public String updateAuthors_Admin(@PathVariable String id, Model model)
    {
        model.addAttribute("author",authorService.findCommandById(Long.valueOf(id)));
        if (loggedAdmin.isCheckLogged())
            return "admin/author/author-update";
        return "redirect:/admin-cpl";
    }

    @PostMapping("author")
    public String updateAuthor(@ModelAttribute AuthorCommand authorCommand)
    {
        AuthorCommand savedCommand = authorService.updateAuthor(authorCommand);
        log.debug("Model author ID: " + authorCommand.getId());
        log.debug("Saved author ID: " + savedCommand.getId());
        return "redirect:/admin-author-list";
    }

    //END OF AUTHOR CONTROL

    //PUBLISHER CONTROL

    @GetMapping("/publisher/new")
    public String newPublisher_Admin(Model model)
    {
        model.addAttribute("publisher", new PublisherCommand());
        if (loggedAdmin.isCheckLogged())
            return "admin/publisher/publisher-new";
        return "redirect:/admin-cpl";
    }

    @PostMapping("newpublisher")
    public String newPublisher(@ModelAttribute PublisherCommand publisherCommand)
    {
        PublisherCommand savedPublisher = publisherService.addPublisher(publisherCommand);
        return "redirect:/admin-publisher-list";
    }

    @GetMapping("/admin-publisher-list")
    public String listPublishers_Admin(Model model)
    {
        model.addAttribute("publishers", publisherService.findAll());
        if (loggedAdmin.isCheckLogged())
            return "admin/publisher/publisher-list";
        return "redirect:/admin-cpl";
    }

    @GetMapping("/publisher/{id}/delete")
    public String deletePublisher_Admin(@PathVariable String id)
    {
        if (loggedAdmin.isCheckLogged()) {
            publisherService.removeById(Long.valueOf(id));
            return "redirect:/admin-publisher-list";
        }
        return "redirect:/admin-cpl";
    }

    @GetMapping("/publisher/{id}/update")
    public String updatePublisher_Admin(@PathVariable String id, Model model)
    {
        model.addAttribute(publisherService.findPublisherById(Long.valueOf(id)));
        return "admin/publisher/publisher-update";
    }

    @PostMapping("publisher")
    public String updatePublisher(@ModelAttribute PublisherCommand publisherCommand)
    {
        PublisherCommand savedCommand = publisherService.updatePublisher(publisherCommand);
        log.debug("Model command ID: " + publisherCommand.getId());
        log.debug("Saved publisher ID: " + savedCommand.getId());
        return "redirect:/admin-publisher-list";
    }

    //END OF PUBLISHER CONTROL
}
