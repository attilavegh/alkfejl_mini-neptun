package hu.elte.minineptun.controllers;

import hu.elte.minineptun.entities.Role;
import hu.elte.minineptun.entities.Student;

import java.util.List;
import java.util.Optional;

import hu.elte.minineptun.entities.Subject;
import hu.elte.minineptun.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lehel T420
 */

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        Optional<Student> oStudent = studentRepository.getStudentByEmail(email);
        if (!oStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oStudent.get());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
        Optional<Student> oStudent = studentRepository.getStudentByName(name);
        if (!oStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oStudent.get());
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<Subject>> getSubjects(@PathVariable Integer id) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if (!oStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oStudent.get().getSubjects());
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        student.setId(null);
        student.setRole(Role.STUDENT);
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> modifyStudentById(@PathVariable Integer id,
                                                     @RequestBody Student student) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if (!oStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        student.setId(id);
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudentById(@PathVariable Integer id) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if (!oStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        studentRepository.delete(oStudent.get());
        return ResponseEntity.ok().build();
    }
}

