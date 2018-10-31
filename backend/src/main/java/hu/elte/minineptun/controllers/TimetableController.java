package hu.elte.minineptun.controllers;

import hu.elte.minineptun.entities.Student;
import hu.elte.minineptun.entities.Timetable;
import hu.elte.minineptun.repositories.StudentRepository;
import hu.elte.minineptun.repositories.TimetableRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lehel T420
 */

@RestController
public class TimetableController {
    @Autowired
    private TimetableRepository timetableRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    //@Autowired
    //private SubjectRepository subjectRepository;
    
    @GetMapping("")
    public ResponseEntity<Iterable<Timetable>> getAll() {
        Iterable<Timetable> timetables = timetableRepository.findAll();
        return ResponseEntity.ok(timetables);
    }
    
    @PostMapping("")
    public ResponseEntity<Timetable> post (@RequestBody Timetable timetable) {
        timetable.setId(null);
        return ResponseEntity.ok(timetableRepository.save(timetable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Timetable> get(@PathVariable Integer id) {
        Optional<Timetable> oTimetable = timetableRepository.findById(id);
        if (!oTimetable.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oTimetable.get());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Timetable> put (@PathVariable Integer id, @RequestBody Timetable timetable) {
        Optional<Timetable> oTimetable = timetableRepository.findById(id);
        if (!oTimetable.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        timetable.setId(id);
        return ResponseEntity.ok(timetableRepository.save(timetable));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable Integer id) {
        Optional<Timetable> oTimetable = timetableRepository.findById(id);
        if (!oTimetable.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        timetableRepository.delete(oTimetable.get());
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/students")
    public ResponseEntity<Iterable<Student>> getStudents(@PathVariable Integer id){
        Optional<Timetable> oTimetable = timetableRepository.findById(id);
        if (!oTimetable.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oTimetable.get().getStudents());
    }
    
    @PostMapping("/{id}/students")
    public ResponseEntity<Student> postStudents(@PathVariable Integer id, @RequestBody Student student){
        Optional<Timetable> oTimetable = timetableRepository.findById(id);
        if (!oTimetable.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        student.setId(null);
        student.setTimetable(oTimetable.get());
        return ResponseEntity.ok(studentRepository.save(student));
    }
    
    @PutMapping("/{id}/students")
    public ResponseEntity<Iterable<Student>> putStudents(@PathVariable Integer id, @RequestBody List<Student> students){
        Optional<Timetable> oTimetable = timetableRepository.findById(id);
        if (!oTimetable.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        for (Student student: students) {
            Optional<Student> oStudent = studentRepository.findById(id);
            if (!oStudent.isPresent()) {
                continue;
            }
            oStudent.get().setTimetable(oTimetable.get());
            studentRepository.save(oStudent.get());
        }
        return ResponseEntity.ok(oTimetable.get().getStudents());
    }
    
    /*@GetMapping("/{id}/subjects")
    public ResponseEntity<Iterable<Subject>> getSubjects(@PathVariable Integer id){
        Optional<Timetable> oTimetable = timetableRepository.findById(id);
        if (!oTimetable.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oTimetable.get().getSubjects());
    }
    
    @PostMapping("/{id}/subjects")
    public ResponseEntity<Subject> postSubject(@PathVariable Integer id, @RequestBody Subject subject){
        Optional<Timetable> oTimetable = timetableRepository.findById(id);
        if (!oTimetable.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        subject.setId(null);
        subject.setTimetable(oTimetable.get());
        return ResponseEntity.ok(subjectRepository.save(subject));
    }
    
    @PutMapping("/{id}/students")
    public ResponseEntity<Iterable<Subject>> putSubjects(@PathVariable Integer id, @RequestBody List<Subject> subjects){
        Optional<Timetable> oTimetable = timetableRepository.findById(id);
        if (!oTimetable.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        for (Subject subject: subject) {
            Optional<Subject> oSubject = subjectRepository.findById(id);
            if (!oSubject.isPresent()) {
                continue;
            }
            oSubject.get().setTimetable(oTimetable.get());
            studentRepository.save(oSubject.get());
        }
        return ResponseEntity.ok(oTimetable.get().getSubjects());
    }
    */
    
}
