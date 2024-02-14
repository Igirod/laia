package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.ProductDetail;

@Repository
public interface ProductDetailJpaRepository extends JpaRepository<ProductDetail, Long> {

   public List<ProductDetail> findProductDetailsByProductId(Long productId);

}
