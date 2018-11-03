package hu.elte.minineptun.controllers;

import hu.elte.minineptun.entities.Role;
import hu.elte.minineptun.entities.Subject;
import hu.elte.minineptun.entities.Teacher;
import hu.elte.minineptun.repositories.SubjectRepository;
import hu.elte.minineptun.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherRepository.findAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Teacher> getTeacherByEmail(@PathVariable String email) {
        Optional<Teacher> oTeacher = teacherRepository.getTeacherByEmail(email);
        if (!oTeacher.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oTeacher.get());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Teacher> getTeacherByName(@PathVariable String name) {
        Optional<Teacher> oTeacher = teacherRepository.getTeacherByName(name);
        if (!oTeacher.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oTeacher.get());
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<Subject>> getSubjectsByTeacherId(@PathVariable Integer id) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        if (!oTeacher.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oTeacher.get().getSubjectList());
    }

    @PostMapping("/add")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        teacher.setId(null);
        teacher.setRole(Role.TEACHER);
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }

    @PostMapping("/{id}/subject/add")
    public ResponseEntity<Subject> addSubjectByTeacherId(@PathVariable Integer id,
                                                         @RequestBody Subject subject) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        if (!oTeacher.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        subject.setId(null);
        subject.setTeacher(oTeacher.get());
        return ResponseEntity.ok(subjectRepository.save(subject));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> modifyTeacherById(@PathVariable Integer id,
                                                     @RequestBody Teacher teacher) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        if (!oTeacher.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        teacher.setId(oTeacher.get().getId());
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacherByEmail(@PathVariable Integer id) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        if (!oTeacher.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        teacherRepository.delete(oTeacher.get());
        return ResponseEntity.ok().build();
    }
}
