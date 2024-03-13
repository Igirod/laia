package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.SellerQuestionProduct;
import us.kanddys.laia.modules.ecommerce.model.SellerQuestionProductId;

@Repository
public interface SellerQuestionProductJpaRepository extends JpaRepository<SellerQuestionProduct, SellerQuestionProductId> {
}
