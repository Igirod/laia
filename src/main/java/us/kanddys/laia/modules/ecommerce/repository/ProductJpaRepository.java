package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {

   @Query(value = "SELECT id FROM products WHERE id = ?1", nativeQuery = true)
   Optional<Long> findProductIdIfExists(Long id);
}
