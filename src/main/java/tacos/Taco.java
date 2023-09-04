// tag::all[]
// tag::allButValidation[]
package tacos;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




import lombok.Data;


@Data
public class Taco {

    private Long id;
    private Date createdAt;

    @NotNull(message="Pole imię musi zawierać conajmniej jedną literę" )
    @Size(min=5, message="Pole imię musi zawierać conajmniej jedną literę")
    private String name;
    @NotNull(message="Wymagany jest przynajmniej jeden składnik")
    @Size(min=1, message="Wymagany jest przynajmniej jeden składnik")
    private List<Ingredient> ingredients;

}
