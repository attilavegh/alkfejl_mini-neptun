package hu.elte.minineptun.repositories;

import hu.elte.minineptun.entities.Teacher;

import java.util.Optional;

public interface TeacherRepository extends org.springframework.data.repository.CrudRepository<Teacher, Integer> {
    Optional<Teacher> getTeacherByEmail(String email);
    Optional<Teacher> getTeacherByName(String name);
}
