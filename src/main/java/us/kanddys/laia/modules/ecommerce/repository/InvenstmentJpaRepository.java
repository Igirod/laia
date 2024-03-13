package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Invenstment;

@Repository
public interface InvenstmentJpaRepository extends JpaRepository<Invenstment, Long> {

   public List<Invenstment> findByProductId(Long id);
}
