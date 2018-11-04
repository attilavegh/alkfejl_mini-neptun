package hu.elte.minineptun.controllers;

import hu.elte.minineptun.entities.Role;
import hu.elte.minineptun.entities.Student;

import java.util.List;
import java.util.Optional;

import hu.elte.minineptun.entities.Subject;
import hu.elte.minineptun.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping()
    @Secured("ROLE_TEACHER")
    public ResponseEntity<Iterable<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<Student> getStudentByUsername(@PathVariable String username) {
        Optional<Student> oStudent = studentRepository.findByUsername(username);
        if (!oStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oStudent.get());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
        Optional<Student> oStudent = studentRepository.findByName(name);
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

    @PostMapping("/register")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Optional<Student> oStudent = studentRepository.findByUsername(student.getUsername());
        if (oStudent.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        student.setId(null);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRole(Role.ROLE_STUDENT);
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PutMapping("/{id}")
    @Secured("ROLE_TEACHER")
    public ResponseEntity<Student> modifyStudentById(@PathVariable Integer id,
                                                     @RequestBody Student student) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if (!oStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (student.getPassword() == null) {
            student.setPassword(oStudent.get().getPassword());
        } else {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
        }

        student.setId(id);
        student.setRole(Role.ROLE_STUDENT);
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_TEACHER")
    public ResponseEntity deleteStudentById(@PathVariable Integer id) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if (!oStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        studentRepository.delete(oStudent.get());
        return ResponseEntity.ok().build();
    }
}

