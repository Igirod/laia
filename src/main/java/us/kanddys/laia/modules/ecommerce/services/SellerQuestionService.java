package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.SellerQuestionDTO;

/**
 * Esta interfaz define las obligaciones que debe implementar la clase
 * SellerQuestionServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface SellerQuestionService {

   /**
    * Este método se encarga de crear una nueva pregunta para un vendedor.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param question
    * @return Integer
    */
   public Long createQuestion(String question, Optional<Integer> required, Optional<String> type,
         Optional<Integer> limit);

   /**
    * Este método se encarga de eliminar una pregunta para un vendedor.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param questionId
    * @return Integer.
    */
   public Integer deleteQuestion(Long questionId);

   /**
    * Este método se encarga de modificar una pregunta para un vendedor.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param id
    * @param question
    * @return Integer
    */
   public Integer updateQuestion(Long questionId, Optional<String> question, Optional<String> type,
         Optional<Integer> limit,
         Optional<Integer> required);

   /**
    * Este método se encarga de obtener una pregunta de un vendedor por su valor
    * de pregunta y tipo.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param question
    * @param type
    * @return SellerQuestion
    */
   public Long getQuestionIdByQuestionAndType(String question, String type);

   /**
    * Este método se encarga de obtener una pregunta por su valor de pregunta
    * y tipo.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param question
    * @param type
    * @return SellerQuestionDTO
    */
   public SellerQuestionDTO getQuestionByQuestionAndType(String question, String type);
}
