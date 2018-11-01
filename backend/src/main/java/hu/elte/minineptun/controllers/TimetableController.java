package hu.elte.minineptun.controllers;

import hu.elte.minineptun.entities.Student;
import hu.elte.minineptun.entities.Timetable;
import hu.elte.minineptun.repositories.StudentRepository;
import hu.elte.minineptun.repositories.TimetableRepository;
import java.util.Optional;
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
 *
 * @author Lehel T420
 */

@RestController
@RequestMapping("/api/timetables")
public class TimetableController {
    @Autowired
    private TimetableRepository timetableRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
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
    
    @GetMapping("/{id}/student")
    public ResponseEntity<Student> getStudent (@PathVariable Integer id) {
        Optional<Timetable> oTimetable = timetableRepository.findById(id);
        if (!oTimetable.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oTimetable.get().getStudent());
    }
    /*
    @GetMapping("/{id}/subject")
    public ResponseEntity<Subject> getSubject (@PathVariable Integer id) {
        Optional<Timetable> oTimetable = timetableRepository.findById(id);
        if (!oTimetable.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oTimetable.get().getSubject());
    }
    */
}
