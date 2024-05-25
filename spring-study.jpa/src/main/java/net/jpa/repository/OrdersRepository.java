package net.jpa.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import net.jpa.domain.Orders;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrdersRepository {

    private final EntityManager em;

    public void save(Orders orders) {
        em.persist(orders);
    }

    public Orders findOne(Long id) {
        return em.find(Orders.class, id);
    }

}
