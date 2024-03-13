package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.SellerQuestion;

@Repository
public interface SellerQuestionJpaRepository extends JpaRepository<SellerQuestion, Long> {

   @Query(value = "SELECT id FROM sellers_questions WHERE question = ?1 AND type = ?2", nativeQuery = true)
   public Long getQuestionIdByQuestionAndType(String question, String type);
}
