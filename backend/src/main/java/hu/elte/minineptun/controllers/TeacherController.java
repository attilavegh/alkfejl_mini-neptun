package hu.elte.minineptun.controllers;

import hu.elte.minineptun.entities.Role;
import hu.elte.minineptun.entities.Subject;
import hu.elte.minineptun.entities.Teacher;
import hu.elte.minineptun.entities.User;
import hu.elte.minineptun.repositories.SubjectRepository;
import hu.elte.minineptun.repositories.TeacherRepository;
import hu.elte.minineptun.repositories.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        Optional<User> oTeacher = userRepository.findByUsername(teacher.getUsername());
        if (oTeacher.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        teacher.setId(null);
        teacher.setRole(Role.ROLE_TEACHER);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }
}
