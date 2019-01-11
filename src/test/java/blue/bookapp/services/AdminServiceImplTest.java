package blue.bookapp.services;

import blue.bookapp.domain.Admin;
import blue.bookapp.repositories.AdminRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceImplTest {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminService adminService;

    @Before
    public void setUp() throws Exception {
        adminRepository.deleteAll();
    }

    @Test
    public void testLogin() {
        Admin admin = new Admin();
        admin.setUsername("luke");
        admin.setPassword("Foo");

        adminRepository.save(admin);

        assertEquals(1, adminService.getAdmins().size());
        assertTrue(adminService.checkUser("luke"));
        assertTrue(adminService.checkPass("Foo"));
        assertNotNull(adminRepository.findById(1L));
    }
}