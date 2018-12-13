package hu.elte.minineptun.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User implements Serializable {
    @Column
    @NotNull
    private String name;
    
    @Column
    @NotNull
    private Integer yearStarted;
    
    @Column
    @NotNull
    private Integer semester;

    @ManyToMany
    @JoinTable
    private List<Subject> subjects;

    public boolean removeSubject(final int id){
        return subjects.removeIf(subject -> subject.getId() == id);
    }
}

