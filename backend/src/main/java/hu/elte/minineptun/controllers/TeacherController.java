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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.access.annotation.Secured;

import javax.validation.constraints.Null;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/subjects")
    public ResponseEntity<Iterable<Subject>> getSubjects(@RequestHeader("authorization") String token) {
        Optional<Teacher> oTeacher = teacherRepository.findByUsername(getUsername(token));

        if (!oTeacher.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(oTeacher.get().getSubjects());
    }

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

    private String getUsername(String token) {
        String base64Credentials = token.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);

        final String[] credentials = new String(credDecoded, StandardCharsets.UTF_8).split(":", 2);

        return credentials[0];
    }
}
