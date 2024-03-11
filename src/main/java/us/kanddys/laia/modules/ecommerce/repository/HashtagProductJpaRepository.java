package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.HashtagProduct;
import us.kanddys.laia.modules.ecommerce.model.HashtagProductId;

@Repository
public interface HashtagProductJpaRepository extends JpaRepository<HashtagProduct, HashtagProductId> {
}
