package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.AuxiliarMultipleOptionQuestion;

@Repository
public interface AuxiliarMultipleQuestionRepository extends JpaRepository<AuxiliarMultipleOptionQuestion, Long> {
}
