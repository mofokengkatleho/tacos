package katleho.tacos.controller;

import katleho.tacos.model.TacoOrder;
import katleho.tacos.model.User;
import katleho.tacos.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@Slf4j
public class OrderController {
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute TacoOrder tacoOrder) {
        if (tacoOrder.getDeliveryName() == null)
            tacoOrder.setDeliveryName(user.getFullname());

        if (tacoOrder.getDeliveryStreet() == null)
            tacoOrder.setDeliveryStreet(user.getStreet());

        if (tacoOrder.getDeliveryCity() == null)
            tacoOrder.setDeliveryCity(user.getCity());

        if (tacoOrder.getDeliveryState() == null)
            tacoOrder.setDeliveryState(user.getState());

        if (tacoOrder.getDeliveryZip() == null)
            tacoOrder.setDeliveryZip(user.getZip());

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors, SessionStatus status,
                               @AuthenticationPrincipal User user){
        if(errors.hasErrors())
            return "orderForm";

        tacoOrder.setUser(user);
        orderRepository.save(tacoOrder);
//        log.info("Order submitted: {}",tacoOrder);
        status.setComplete();
        return "redirect:/";
    }

}
