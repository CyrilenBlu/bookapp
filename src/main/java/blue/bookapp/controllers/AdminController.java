package blue.bookapp.controllers;

import blue.bookapp.domain.Admin;
import blue.bookapp.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AdminController {

    AdminService adminService;
    private Admin loggedAdmin = new Admin();

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
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
        return "admin/admin-control";
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
}
