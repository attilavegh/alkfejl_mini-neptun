package hu.elte.minineptun.controllers;

import hu.elte.minineptun.entities.Subject;
import hu.elte.minineptun.entities.Teacher;
import hu.elte.minineptun.repositories.StudentRepository;
import hu.elte.minineptun.repositories.SubjectRepository;
import hu.elte.minineptun.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.springframework.security.access.annotation.Secured;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Integer id) {
        Optional<Subject> oSubject = subjectRepository.findById(id);
        if (!oSubject.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oSubject.get());
    }

    @PostMapping()
    @Secured("ROLE_TEACHER")
    public ResponseEntity<Subject> addSubjectByTeacherId(@RequestHeader("authorization") String token,
                                                         @RequestBody Subject subject) {
        Optional<Teacher> oTeacher = teacherRepository.findByUsername(getUsername(token));
        if (!oTeacher.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        subject.setId(null);
        subject.setTeacher(oTeacher.get());
        subject.setStudents(null);
        return ResponseEntity.ok(subjectRepository.save(subject));
    }

    @PutMapping("/{id}")
    @Secured("ROLE_TEACHER")
    public ResponseEntity<Subject> modifySubjectById(@RequestHeader("authorization") String token,
                                                     @PathVariable Integer id,
                                                     @RequestBody Subject subject) {
        Optional<Subject> oSubject = subjectRepository.findById(id);
        if (!oSubject.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (!oSubject.get().getTeacher().getUsername().equals(getUsername(token))) {
           return ResponseEntity.badRequest().build();
        }

        subject.setId(id);
        subject.setTeacher(oSubject.get().getTeacher());
        subject.setStudents(oSubject.get().getStudents());

        return ResponseEntity.ok(subjectRepository.save(subject));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_TEACHER")
    public ResponseEntity<Subject> deleteSubjectById(@RequestHeader("authorization") String token,
                                                     @PathVariable Integer id) {
        Optional<Subject> oSubject = subjectRepository.findById(id);
        if (!oSubject.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (!oSubject.get().getTeacher().getUsername().equals(getUsername(token))) {
            return ResponseEntity.badRequest().build();
        }

        studentRepository.findAll().forEach(student -> student.removeSubject(id));
        subjectRepository.delete(oSubject.get());

        return ResponseEntity.ok(oSubject.get());
    }

    private String getUsername(String token) {
        String base64Credentials = token.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);

        final String[] credentials = new String(credDecoded, StandardCharsets.UTF_8).split(":", 2);

        return credentials[0];
    }
}
