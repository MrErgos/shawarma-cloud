package com.springstudy.shawarma_cloud.repository;

import com.springstudy.shawarma_cloud.model.Ingredient;
import com.springstudy.shawarma_cloud.model.Shawarma;
import com.springstudy.shawarma_cloud.model.ShawarmaOrder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private final JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public ShawarmaOrder save(ShawarmaOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Shawarma_Order "
                + "(delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, "
                + "cc_number, cc_expiration, cc_cvv, placed_at) "
                + "values (?,?,?,?,?,?,?,?,?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);
        order.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getPlacedAt()
                )
        );
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, generatedKeyHolder);
        long orderId = generatedKeyHolder.getKey().longValue();
        order.setId(orderId);

        List<Shawarma> shawarmas = order.getShawarmas();
        int i = 0;
        for (Shawarma shawarma : shawarmas) {
            saveShawarma(orderId, i++, shawarma);
        }
        return order;
    }

    private long saveShawarma(long orderId, int orderKey, Shawarma shawarma) {
        shawarma.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Shawarma "
                + "(name, shawarma_order, shawarma_order_key, created_at) "
                + "values (?,?,?,?)",
                Types.VARCHAR, Types.BIGINT, Types.BIGINT, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        shawarma.getName(),
                        orderId,
                        orderKey,
                        shawarma.getCreatedAt()
                )
        );
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, generatedKeyHolder);
        long shawarmaId = generatedKeyHolder.getKey().longValue();
        shawarma.setId(shawarmaId);

        saveIngredientRefs(shawarmaId, shawarma.getIngredients());

        return shawarmaId;
    }

    private void saveIngredientRefs(long shawarmaId,
            @NotNull @Size(min = 1, message = "Вы должны выбрать хотя бы 1 ингредиент") List<Ingredient> ingredients) {
        int shawarma_key = 0;
        for (Ingredient ingredient : ingredients) {
            jdbcOperations.update(
                    "insert into Ingredient_Ref (ingredient, shawarma, shawarma_key) "
                    + "values (?,?,?)",
                    ingredient.getId(), shawarmaId, shawarma_key++
            );
        }
    }
}
