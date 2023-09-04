// tag::all[]
// tag::allButValidation[]
package tacos;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;



@Data
@Entity
public class Taco {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt;

    @NotNull(message="Pole imię musi zawierać conajmniej jedną literę" )
    @Size(min=5, message="Pole imię musi zawierać conajmniej jedną literę")
    private String name;
    @ManyToMany
    @NotNull(message="Wymagany jest przynajmniej jeden składnik")
    @Size(min=1, message="Wymagany jest przynajmniej jeden składnik")
    private List<Ingredient> ingredients;
    @PrePersist
    void createdAt(){
        this.createdAt=new Date();
    }
}
