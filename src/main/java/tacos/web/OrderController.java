package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.Order;
import tacos.Taco;
import tacos.data.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
private OrderRepository orderRepository;

public OrderController(OrderRepository orderRepository){
    this.orderRepository=orderRepository;
}
    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order",new Order());
        return "orderForm";
    }
    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order") Order order, Errors errors, Model model, SessionStatus sessionStatus){
        if(errors.hasErrors()){
            log.info("Błąd przy walidacji danych zamówienia "+errors);
            model.addAttribute("order",order);
            return "orderForm";
        }
        log.info("Zamówienie zostało złożone "+order);
orderRepository.save(order);
sessionStatus.setComplete();

        return "redirect:/";
    }
}
