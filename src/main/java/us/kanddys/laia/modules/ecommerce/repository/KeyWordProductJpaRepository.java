package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.KeyWordProduct;
import us.kanddys.laia.modules.ecommerce.model.KeyWordProductId;

@Repository
public interface KeyWordProductJpaRepository extends JpaRepository<KeyWordProduct, KeyWordProductId> {
}
