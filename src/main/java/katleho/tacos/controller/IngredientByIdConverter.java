package katleho.tacos.controller;

import katleho.tacos.model.Ingredient;
import katleho.tacos.repository.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter  implements Converter<String,Ingredient> {
    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String ingredientId) {
        return ingredientRepository.findById(ingredientId).orElse(null);
    }
}
