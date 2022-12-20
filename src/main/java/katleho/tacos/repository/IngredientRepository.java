package katleho.tacos.repository;

import katleho.tacos.model.Ingredient;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface IngredientRepository extends Repository<Ingredient,String> {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
