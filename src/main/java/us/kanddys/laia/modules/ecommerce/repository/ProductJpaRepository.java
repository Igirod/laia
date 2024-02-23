package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {

   @Modifying
   @Query(value = "SELECT id FROM products WHERE id = ?1", nativeQuery = true)
   Optional<Long> findProductIdIfExists(Long id);

   @Modifying
   @Query(value = "SELECT stock FROM products WHERE id = ?1", nativeQuery = true)
   Integer findStockByProductId(Long id);

   @Modifying
   @Query(value = "UPDATE products SET status = :status WHERE id = :productId", nativeQuery = true)
   Integer updateStatusByProductId(Long productId, Integer status);

   @Modifying
   @Query(value = "UPDATE products SET stock = :stock WHERE id = :productId", nativeQuery = true)
   Integer updateStockByProductId(Long productId, Integer stock);
}
