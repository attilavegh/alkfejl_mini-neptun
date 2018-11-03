package hu.elte.minineptun.repositories;

import hu.elte.minineptun.entities.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {
    Optional<Subject> getSubjectByName(String name);
}
