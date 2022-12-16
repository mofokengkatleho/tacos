package katleho.tacos.model;

import lombok.Data;


import java.util.List;

@Data
public class Taco {
    String name;
    private List<Ingredient> ingredients;
}
