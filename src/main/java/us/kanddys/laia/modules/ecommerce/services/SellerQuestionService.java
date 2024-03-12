package us.kanddys.laia.modules.ecommerce.services;

import java.util.Optional;

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
   public Integer updateQuestion(Long questionId, String question, String type, Optional<Integer> limit,
         Optional<Integer> required, Optional<String> newType, Optional<String> newQuestion);
}
