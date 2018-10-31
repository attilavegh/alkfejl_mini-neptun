package hu.elte.minineptun.repositories;

import hu.elte.minineptun.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lehel T420
 */

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer>{
 
}
