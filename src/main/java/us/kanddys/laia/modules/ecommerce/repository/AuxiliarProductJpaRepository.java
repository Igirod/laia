package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.AuxiliarProduct;

@Repository
public interface AuxiliarProductJpaRepository extends JpaRepository<AuxiliarProduct, Long> {
}