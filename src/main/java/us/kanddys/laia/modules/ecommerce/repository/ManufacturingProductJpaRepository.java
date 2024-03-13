package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.ManufacturingProduct;

@Repository
public interface ManufacturingProductJpaRepository extends JpaRepository<ManufacturingProduct, Long> {

   @Query(value = "SELECT * FROM manufacturing_product WHERE product_id = ?1", nativeQuery = true)
   public ManufacturingProduct findByProductId(Long id);

}
