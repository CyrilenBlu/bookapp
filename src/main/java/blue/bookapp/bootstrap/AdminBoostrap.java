package blue.bookapp.bootstrap;

import blue.bookapp.domain.Admin;
import blue.bookapp.repositories.AdminRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminBoostrap implements ApplicationListener<ContextRefreshedEvent> {

    AdminRepository adminRepository;

    public AdminBoostrap(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
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
