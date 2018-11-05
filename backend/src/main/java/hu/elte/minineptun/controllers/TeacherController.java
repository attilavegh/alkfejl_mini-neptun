package hu.elte.minineptun.controllers;

import hu.elte.minineptun.entities.Role;
import hu.elte.minineptun.entities.Subject;
import hu.elte.minineptun.entities.Teacher;
import hu.elte.minineptun.repositories.SubjectRepository;
import hu.elte.minineptun.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.security.access.annotation.Secured;

import javax.validation.constraints.Null;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping()
    @Secured("ROLE_TEACHER")
    public ResponseEntity<Iterable<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherRepository.findAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<Teacher> getTeacherByUsername(@PathVariable String username) {
        Optional<Teacher> oTeacher = teacherRepository.findByUsername(username);
        if (!oTeacher.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oTeacher.get());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Teacher> getTeacherByName(@PathVariable String name) {
        Optional<Teacher> oTeacher = teacherRepository.findByName(name);
        if (!oTeacher.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oTeacher.get());
    }

    @PostMapping("/register")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        Optional<Teacher> oTeacher = teacherRepository.findByUsername(teacher.getUsername());
        if (oTeacher.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        teacher.setId(null);
        teacher.setRole(Role.ROLE_TEACHER);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }

    @PutMapping("/{id}")
    @Secured("ROLE_TEACHER")
    public ResponseEntity<Teacher> modifyTeacherById(@PathVariable Integer id,
                                                     @RequestBody Teacher teacher) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        if (!oTeacher.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (teacher.getPassword() == null) {
            teacher.setPassword(oTeacher.get().getPassword());
        } else {
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        }

        teacher.setId(oTeacher.get().getId());
        teacher.setRole(Role.ROLE_TEACHER);
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_TEACHER")
    public ResponseEntity deleteTeacherByEmail(@PathVariable Integer id) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        if (!oTeacher.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        teacherRepository.delete(oTeacher.get());
        return ResponseEntity.ok().build();
    }
}
