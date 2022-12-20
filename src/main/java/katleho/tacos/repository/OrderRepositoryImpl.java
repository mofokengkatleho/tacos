package katleho.tacos.repository;

import katleho.tacos.model.TacoOrder;
import org.springframework.jdbc.core.JdbcOperations;

public class OrderRepositoryImpl implements OrderRepository{
    private JdbcOperations jdbcOperations;

    public OrderRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public TacoOrder save(TacoOrder tacoOrder) {
        return null;
    }
}
