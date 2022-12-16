package katleho.tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@Slf4j
public class OrderController {
    @GetMapping
    public String orderForm(){
        return "orderForm";
    }

}
