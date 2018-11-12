package blue.bookapp.repositories;

import blue.bookapp.domain.Pages;
import org.springframework.data.repository.CrudRepository;

public interface PagesRepository extends CrudRepository<Pages, Long> {
}
