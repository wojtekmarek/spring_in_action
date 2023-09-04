package tacos.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import tacos.Order;
import tacos.Taco;
import tacos.Ingredient;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientRepository ingredirntRepository;
    private  TacoRepository designRepo;
    @ModelAttribute(name="order")
    public Order order(){
        return new Order();
    }
    @ModelAttribute(name="taco")
    public Taco taco(){
        return new Taco();
    }
    @Autowired
    public DesignTacoController(IngredientRepository ingredirntRepository, TacoRepository designRepo){
        this.ingredirntRepository=ingredirntRepository;
        this.designRepo=designRepo;
    }
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {

        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());

    }

private  List<Ingredient> getIngredientFromDB(Model model){
    List<Ingredient> ingredients= new ArrayList<>();
    ingredirntRepository.findAll().forEach(ingredients::add);

    Ingredient.Type[] types = Ingredient.Type.values();
    for (Ingredient.Type type : types) {
        model.addAttribute(type.toString().toLowerCase(),
                filterByType(ingredients, type));
    }
        return ingredients;
}
    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients=getIngredientFromDB(model);
     model.addAttribute("design", new Taco());
        model.addAttribute("fields",new Object() );
     return "design";

    }
    @PostMapping()
    public String processDesign(@Valid @ModelAttribute("design") Taco design , BindingResult errors, Model model,@ModelAttribute Order order) {

        if (errors.hasErrors()) {
            log.info("error design: " +design+errors);
            List<Ingredient> ingredients=getIngredientFromDB(model);
                       return "design";
        }
        log.info("Processing design: " + design);
        Taco saved=designRepo.save(design);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }
    }

