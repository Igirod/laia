package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   @Modifying
   @Query(value = "UPDATE products SET front_page = ?2 WHERE id = ?1", nativeQuery = true)
   public void updateFrontPage(Long productId, String image);
}
