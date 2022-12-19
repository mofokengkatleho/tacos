package katleho.tacos.repository;

import katleho.tacos.model.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById();
    Ingredient save(Ingredient ingredient);
}
