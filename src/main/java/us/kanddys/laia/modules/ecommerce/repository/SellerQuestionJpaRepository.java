package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.SellerQuestion;

@Repository
public interface SellerQuestionJpaRepository extends JpaRepository<SellerQuestion, Long> {

   @Query(value = "SELECT id FROM sellers_questions WHERE type = :type AND question = :question", nativeQuery = true)
   public Optional<SellerQuestion> findIdByTypeAndQuestion(String type, String question);
}
