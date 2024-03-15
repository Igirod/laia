package us.kanddys.laia.modules.ecommerce.services;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * MultipleQuestionOptionServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface MultipleQuestionOptionService {

   /**
    * Este método se encarga de crear una nueva opcion para una pregunta de tipo
    * MULTIPLE.
    * 
    * @param questionId
    * @param option
    * @return Integer
    */
   public Integer createOption(Long questionId, String option);

   /**
    * Este método se encarga de eliminar una opcion para una pregunta de tipo
    * MULTIPLE.
    * 
    * @param optionId
    * @return Integer
    */
   public Integer deleteOption(Long optionId);

   /**
    * Este método se encarga de modificar una opcion para una pregunta de tipo
    * MULTIPLE.
    * 
    * @param optionId
    * @param option
    * @return Integer
    */
   public Integer updateOption(Long optionId, String option);
}
