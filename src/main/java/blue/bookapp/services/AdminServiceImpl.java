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
            if (admin.getUsername() == username)
            {
                savedAdmin.add(admin);
            }
        });
        return savedAdmin.size() > 0 | true | false;
    }

    @Override
    public boolean checkPass(String password) {
        return false;
    }

    @Override
    public Set<Admin> getAdmins() {
        Set<Admin> adminSet = new HashSet<>();
        adminRepository.findAll().iterator().forEachRemaining(adminSet::add);
        return adminSet;
    }
}
