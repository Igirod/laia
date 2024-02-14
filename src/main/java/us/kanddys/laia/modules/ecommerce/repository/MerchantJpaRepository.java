package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Merchant;

@Repository
public interface MerchantJpaRepository extends JpaRepository<Merchant, Long> {
   Optional<Merchant> findBySlug(String slug);
}
