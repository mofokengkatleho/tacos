package katleho.tacos.repository;

import katleho.tacos.model.Ingredient;

import java.util.Optional;

public class IngredientRepositoryImpl implements IngredientRepository{
    @Override
    public Iterable<Ingredient> findAll() {
        return null;
    }

    @Override
    public Optional<Ingredient> findById() {
        return Optional.empty();
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return null;
    }
}
