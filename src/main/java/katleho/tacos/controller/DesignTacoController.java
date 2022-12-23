package katleho.tacos.controller;

import katleho.tacos.model.Ingredient;
import katleho.tacos.model.Ingredient.Type;
import katleho.tacos.model.Taco;

import katleho.tacos.model.TacoOrder;
import katleho.tacos.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;

    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        Arrays.stream(Ingredient.Type.values())
                .forEach(ingredientType -> model.addAttribute(ingredientType.toString().toLowerCase(),
                        filterByType(ingredients,ingredientType)));
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type){
        return StreamSupport.stream(ingredients.spliterator(),false)
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String renderTacoDesignForm(){
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){
        if (errors.hasErrors())
            return "design";
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}",taco);
        return "redirect:/orders/current";
    }
}
