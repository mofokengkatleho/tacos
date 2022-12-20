package katleho.tacos.repository;

import katleho.tacos.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {
    TacoOrder save(TacoOrder tacoOrder);
}
