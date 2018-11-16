package blue.bookapp.controllers;

import blue.bookapp.services.AdminService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class AdminController {

    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("admin-cpl")
    public String login(Model model)
    {
        model.addAttribute("admins", adminService.getAdmins());
        return "blank";
    }
}
