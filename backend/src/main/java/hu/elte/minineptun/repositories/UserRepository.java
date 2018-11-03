package hu.elte.minineptun.repositories;

import hu.elte.minineptun.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepository extends UserBaseRepository<User> {
    Optional<User> getUserByEmail(String email);
    Optional<User> findByUsername(String username);
}
