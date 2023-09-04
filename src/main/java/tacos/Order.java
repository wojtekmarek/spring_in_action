package tacos;

import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

@Data
public class Order {
    private Long id;
    private Date placedAt;
    @NotBlank(message="Imie jest wymahgane")
    private String name;
    @NotBlank(message="Ulica jest wymagana")
    private String street;
    @NotBlank(message="Miasto jest wymagane")
    private String city;
    @NotBlank(message = "Województwo jest wymagane")
    private String state;
    @NotBlank(message = "Kod pocztowy jest wymagany")
    private String zip;
    @CreditCardNumber(message="Not a valid credit card number")
       private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="wymagany format miesiąc(MM)/rok(YY)")
      private String ccExpiration;
    @Digits(message = "niewłaściwy CVV", integer = 3, fraction = 0)
     private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();
    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

}
