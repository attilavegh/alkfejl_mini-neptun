package hu.elte.minineptun.controllers;

import hu.elte.minineptun.entities.Student;
import hu.elte.minineptun.entities.Subject;
import hu.elte.minineptun.entities.Teacher;
import hu.elte.minineptun.repositories.SubjectRepository;
import hu.elte.minineptun.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.security.access.annotation.Secured;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

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

    @GetMapping("/name/{name}")
    public ResponseEntity<Subject> getSubjectByName(@PathVariable String name) {
        Optional<Subject> oSubject = subjectRepository.findByName(name);
        if (!oSubject.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oSubject.get());
    }

    @GetMapping("/{id}/teacher")
    public ResponseEntity<Teacher> getTeacherBySubjectId(@PathVariable Integer id) {
        Optional<Subject> oSubject = subjectRepository.findById(id);
        if (!oSubject.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oSubject.get().getTeacher());
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getStudentsBySubjectId(@PathVariable Integer id) {
        Optional<Subject> oSubject = subjectRepository.findById(id);
        if (!oSubject.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oSubject.get().getStudents());
    }

    @PostMapping("/{teacherId}")
    @Secured("ROLE_TEACHER")
    public ResponseEntity<Subject> addSubjectByTeacherId(@PathVariable Integer teacherId,
                                                         @RequestBody Subject subject) {
        Optional<Teacher> oTeacher = teacherRepository.findById(teacherId);
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
    public ResponseEntity<Subject> modifySubjectById(@PathVariable Integer id,
                                                     @RequestBody Subject subject) {
        Optional<Subject> oSubject = subjectRepository.findById(id);
        if (!oSubject.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        subject.setId(id);
        subject.setTeacher(oSubject.get().getTeacher());
        subject.setStudents(oSubject.get().getStudents());
        return ResponseEntity.ok(subjectRepository.save(subject));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_TEACHER")
    public ResponseEntity<Subject> deleteSubjectById(@PathVariable Integer id) {
        Optional<Subject> oSubject = subjectRepository.findById(id);
        if (!oSubject.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        subjectRepository.delete(oSubject.get());
        return ResponseEntity.ok(oSubject.get());
    }
}
