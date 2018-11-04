package hu.elte.minineptun.repositories;

import hu.elte.minineptun.entities.Student;

import java.util.Optional;

public interface StudentRepository extends org.springframework.data.repository.CrudRepository<Student, Integer> {
    Optional<Student> findByUsername(String username);
    Optional<Student> findByName(String email);
}
