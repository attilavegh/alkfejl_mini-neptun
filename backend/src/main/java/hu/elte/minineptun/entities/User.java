package hu.elte.minineptun.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.InheritanceType.JOINED;

@Entity
@Data
@Inheritance(strategy = JOINED)
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(unique = true)
    @NotNull
    protected String username;

    @Column
    @NotNull
    protected String password;

    @Column(updatable = false)
    @CreatedDate
    protected Date createdOn;

    @Column
    @LastModifiedDate
    protected Date modifiedOn;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    protected Role role;
}
