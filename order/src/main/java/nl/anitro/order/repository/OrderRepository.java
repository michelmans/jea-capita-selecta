package nl.anitro.order.repository;

import nl.anitro.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{

    List<Order> findByUsername(@Param("username") String username);

}
