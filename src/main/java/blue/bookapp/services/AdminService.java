package blue.bookapp.services;

import blue.bookapp.domain.Admin;

import java.util.Set;

public interface AdminService {
    boolean checkUser(String username);
    boolean checkPass(String password);

    Set<Admin> getAdmins();
}
