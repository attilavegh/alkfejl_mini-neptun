package hu.elte.minineptun.repositories;

import hu.elte.minineptun.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends CrudRepository<T, Integer> {
}
