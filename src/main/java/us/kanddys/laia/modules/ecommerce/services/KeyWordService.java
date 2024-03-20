package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.KeyWordDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.KeyWordInputDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * KeyWordServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface KeyWordService {

   /**
    * Este método tiene la obligación de agregar una palabra clave.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param word
    * @param userId
    * @return Long
    */
   public Long createKeyWord(String word, Long userId);

   /**
    * Este método tiene la obligación de modificar una keyword.
    *
    * @param keywordId
    * @param word
    * @return Integer
    */
   public Integer updateKeyWord(Long keywordId, String word);

   /**
    * Este método tiene la obligación de eliminar una keyword.
    *
    * @param keywordId
    * @return Integer
    */
   public Integer deleteKeyWord(Long keywordId);

   /**
    * Este método tiene la obligación de obtener el id de una keyword por su
    * palabra asociada.
    *
    * @param keywordValue
    * @return Long
    */
   public Long getKeywordId(String keywordValue);

   /**
    * Este método tiene la obligación de obtener todas las keywords asociadas a un
    * producto.
    *
    * @param productId
    * @return List
    */
   public List<KeyWordDTO> getKeywordsByProductId(Long productId);

   /**
    * Método que tiene la obligación de obtener un listado de 5 keyWords.
    * por su valor y el id del usuario asociado.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param keyWordValue
    * @param userId
    * @return List<KeyWordDTO>
    */
   public List<KeyWordDTO> getKeywordsByValueAndUserid(Long userId, Optional<String> keyWordValue);

   /**
    * Método que tiene la obligación de actualizar las palabras claves asociadas a
    * un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @param userId
    * @param keyWords
    * @return List<KeyWordDTO>
    */
   public List<KeyWordDTO> updateKeywordsByProductId(Long productId, Long userId, List<KeyWordInputDTO> keyWords);
}
