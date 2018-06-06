package nl.anitro.bakapp.repository;

import nl.anitro.bakapp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{

    Optional<Product> findByProductname(String productname);
    List<Product> findByProductnameContaining(String productname);
    Optional<Product> findByUnitsIsLessThan(String units);

}
