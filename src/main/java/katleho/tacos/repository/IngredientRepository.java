package katleho.tacos.repository;

import katleho.tacos.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient,String> {
}
