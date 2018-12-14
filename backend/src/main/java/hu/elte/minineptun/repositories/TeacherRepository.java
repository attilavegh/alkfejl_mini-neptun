package hu.elte.minineptun.repositories;

import hu.elte.minineptun.entities.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
    Optional<Teacher> findByUsername(String username);
}
