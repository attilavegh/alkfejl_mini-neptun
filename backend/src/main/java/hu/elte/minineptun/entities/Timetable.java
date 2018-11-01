package hu.elte.minineptun.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Lehel T420
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Timetable implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JsonIgnore
    @JoinColumn
    @ManyToOne
    private Student student;
    
    /*
    @JsonIgnore
    @JoinColumn
    @ManyToOne
    private Subject subject;
    */
    
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;
}
