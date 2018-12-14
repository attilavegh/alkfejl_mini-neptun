package hu.elte.minineptun.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String day;

    @Column
    @NotNull
    private String time;

    @Column
    @NotNull
    private String location;

    @JoinColumn
    @ManyToOne
    private Teacher teacher;

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private List<Student> students;
}
