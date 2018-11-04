package hu.elte.minineptun.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends User implements Serializable {
    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String department;

    @Column
    @NotNull
    private String room;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjectList;
}
