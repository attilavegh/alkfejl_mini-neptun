package hu.elte.minineptun.repositories;

import hu.elte.minineptun.entities.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepository extends UserBaseRepository<User> {
    Optional<User> findByUsername(String username);
}
