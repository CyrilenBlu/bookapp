package blue.bookapp.services;

import blue.bookapp.domain.Admin;
import blue.bookapp.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService{

    AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public boolean checkUser(String username) {
        Set<Admin> adminSet = new HashSet<>();
        adminRepository.findAll().iterator().forEachRemaining(adminSet::add);
        Set<Admin> savedAdmin = new HashSet<>();
        adminSet.stream().forEach(admin ->
        {
            if (admin.getUsername().equals(username))
            {
                savedAdmin.add(admin);
            }
        });
        System.out.println("user check: " + username);
        System.out.println("user size: "+ savedAdmin.size());
        if (savedAdmin.size() > 0)
            return true;
        return false;
    }

    @Override
    public boolean checkPass(String password) {
        Set<Admin> adminSet = new HashSet<>();
        adminRepository.findAll().iterator().forEachRemaining(adminSet::add);
        Set<Admin> savedAdmin = new HashSet<>();
        adminSet.stream().forEach(admin ->
        {
            if (admin.getPassword().equals(password))
            {

                savedAdmin.add(admin);
            }
        });
        if (savedAdmin.size() > 0)
            return true;
        return false;
    }

    @Override
    public Set<Admin> getAdmins() {
        Set<Admin> adminSet = new HashSet<>();
        adminRepository.findAll().iterator().forEachRemaining(adminSet::add);
        return adminSet;
    }
}
