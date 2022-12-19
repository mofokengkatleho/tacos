package katleho.tacos.model;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min=5,message="Name must be at least 5 characters long")
    String name;
    @NotNull
    @Size(min = 1,message = "You must select at least one ingredient")
    private List<Ingredient> ingredients;
}
