package hu.elte.minineptun.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Lehel T420
 */

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

    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    private List<Subject> subjects;
}

