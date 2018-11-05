package hu.elte.minineptun.repositories;

import hu.elte.minineptun.entities.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Optional<Student> findByUsername(String username);
    Optional<Student> findByName(String email);
}
