package hu.elte.minineptun.controllers;

import hu.elte.minineptun.entities.Role;
import hu.elte.minineptun.entities.Student;

import java.nio.charset.StandardCharsets;
import java.util.*;

import hu.elte.minineptun.entities.Subject;
import hu.elte.minineptun.entities.User;
import hu.elte.minineptun.repositories.StudentRepository;
import hu.elte.minineptun.repositories.SubjectRepository;
import hu.elte.minineptun.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> getSubjects(@RequestHeader("authorization") String token) {
        Optional<Student> oStudent = studentRepository.findByUsername(getUsername(token));
        if (!oStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oStudent.get().getSubjects());
    }

    @PostMapping("/register")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Optional<User> oStudent = userRepository.findByUsername(student.getUsername());

        if (oStudent.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        student.setId(null);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRole(Role.ROLE_STUDENT);
        student.setSemester(1);
        student.setYearStarted(Calendar.getInstance().get(Calendar.YEAR));
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PatchMapping("/subjects")
    public ResponseEntity<Student> manageSubjects(@RequestHeader("authorization") String token,
                                                  @RequestBody Student student) {
        if (student.getSubjects() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Student> oStudent = studentRepository.findByUsername(student.getUsername());
        if (!oStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (!oStudent.get().getUsername().equals(getUsername(token))) {
            return ResponseEntity.badRequest().build();
        }

        for (Subject subject: student.getSubjects()) {
            Optional<Subject> oSubject = subjectRepository.findById(subject.getId());
            if (!oSubject.isPresent()) {
                return ResponseEntity.notFound().build();
            }
        }

        oStudent.get().setSubjects(student.getSubjects());
        return ResponseEntity.ok(studentRepository.save(oStudent.get()));
    }


    private String getUsername(String token) {
        String base64Credentials = token.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);

        final String[] credentials = new String(credDecoded, StandardCharsets.UTF_8).split(":", 2);

        return credentials[0];
    }
}

