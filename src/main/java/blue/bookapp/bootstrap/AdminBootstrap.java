package blue.bookapp.bootstrap;

import blue.bookapp.domain.Admin;
import blue.bookapp.repositories.AdminRepository;
import blue.bookapp.services.AdminService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    AdminRepository adminRepository;
    AdminService adminService;

    public AdminBootstrap(AdminRepository adminRepository, AdminService adminService) {
        this.adminRepository = adminRepository;
        this.adminService = adminService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        adminRepository.saveAll(getAdmins());
    }

    private List<Admin> getAdmins()
    {
        List<Admin> admins = new ArrayList<>();
        Admin admin = new Admin();
        admin.setUsername("lukepetzer");
        admin.setPassword("123test");

        admins.add(admin);
        return admins;
    }
}
