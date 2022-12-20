package katleho.tacos.repository;

import katleho.tacos.model.IngredientRef;
import katleho.tacos.model.Taco;
import katleho.tacos.model.TacoOrder;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository{
    private JdbcOperations jdbcOperations;

    public OrderRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder tacoOrder) {
        PreparedStatementCreatorFactory preparedStatementCreatorFactory =
                new PreparedStatementCreatorFactory(
                        "insert into Taco_Order "
                                + "(delivery_name, delivery_street, delivery_city, "
                                + "delivery_state, delivery_zip, cc_number, "
                                + "cc_expiration, cc_cvv, placed_at) "
                                + "values (?,?,?,?,?,?,?,?,?)",
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
                );
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        tacoOrder.setPlacedAt(new Date());
        PreparedStatementCreator preparedStatementCreator =
                preparedStatementCreatorFactory.newPreparedStatementCreator(
                        Arrays.asList(
                                tacoOrder.getDeliveryName(),
                                tacoOrder.getDeliveryStreet(),
                                tacoOrder.getDeliveryCity(),
                                tacoOrder.getDeliveryState(),
                                tacoOrder.getDeliveryZip(),
                                tacoOrder.getCcNumber(),
                                tacoOrder.getCcExpiration(),
                                tacoOrder.getCcCVV(),
                                tacoOrder.getPlacedAt()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(preparedStatementCreator, keyHolder);
        long tacoOrderId = keyHolder.getKey().longValue();
        tacoOrder.setId(tacoOrderId);

        List<Taco> tacos = tacoOrder.getTacos();
        int i=0;
        for (Taco taco : tacos) {
            saveTaco(tacoOrderId, i++, taco);
        }
        return tacoOrder;
    }
    private long saveTaco(Long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory preparedStatementCreatorFactory =
                new PreparedStatementCreatorFactory(
                        "insert into Taco "
                                + "(name, created_at, taco_order, taco_order_key) "
                                + "values (?, ?, ?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
                );
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator preparedStatementCreator =
                preparedStatementCreatorFactory.newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                taco.getCreatedAt(),
                                orderId,
                                orderKey));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(preparedStatementCreator, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);

        saveIngredientRefs(tacoId, taco.getIngredients());

        return tacoId;
    }

    private void saveIngredientRefs(
            long tacoId, List<IngredientRef> ingredientRefs) {
    }
    }
}
