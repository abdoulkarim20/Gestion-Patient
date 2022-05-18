package cours.springboot.simplon.boostrapspringboot.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patients")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(length = 200)
    private String nomComplet;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private int score;
    private boolean malade;
}
