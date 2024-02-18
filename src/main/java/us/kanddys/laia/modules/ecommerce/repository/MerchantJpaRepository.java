package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Merchant;

@Repository
public interface MerchantJpaRepository extends JpaRepository<Merchant, Long> {
   
   public Optional<Merchant> findBySlug(String slug);

   @Query(value = "SELECT id FROM merchants WHERE id = :merchantId", nativeQuery = true)
   public Long existByMerchantId(Long merchantId);

}
