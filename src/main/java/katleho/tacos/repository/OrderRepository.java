package katleho.tacos.repository;

import katleho.tacos.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder tacoOrder);
}
