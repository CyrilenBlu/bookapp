package blue.bookapp.repositories;

import blue.bookapp.domain.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
}
