package katleho.tacos.model;

import lombok.Data;

@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    private enum Type{
        WRAP,SAUCE,CHEESE,VEGGIES,PROTEIN
    }
}
