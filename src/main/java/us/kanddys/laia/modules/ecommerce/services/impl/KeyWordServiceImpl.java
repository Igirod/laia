package us.kanddys.laia.modules.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.exception.ExistingKeyWordException;
import us.kanddys.laia.modules.ecommerce.exception.KeyWordNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.KeyWord;
import us.kanddys.laia.modules.ecommerce.repository.KeyWordJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.KeyWordService;

/**
 * Esta clase contiene las implementaciones de las obligaciones de la interface
 * KeyWordService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class KeyWordServiceImpl implements KeyWordService {

   @Autowired
   private KeyWordJpaRepository keyWordJpaRepository;

   @Override
   public Long createKeyWord(String word) {
      if (keyWordJpaRepository.findKeyWordIdByWord(word) != null)
         throw new ExistingKeyWordException(ExceptionMessage.EXISTING_KEY_WORD);
      return keyWordJpaRepository.save(new KeyWord(null, word)).getId();
   }

   @Override
   public Integer updateKeyWord(Long keywordId, String word) {
      var existKeyWord = keyWordJpaRepository.findById(keywordId);
      if (existKeyWord.isEmpty())
         throw new KeyWordNotFoundException(ExceptionMessage.KEY_WORD_NOT_FOUND);
      if (word != null) {
         var keyWordToUpdate = existKeyWord.get();
         keyWordToUpdate.setWord(word);
         keyWordJpaRepository.save(keyWordToUpdate);
      } else {
         deleteKeyWord(keywordId);
      }
      return 1;
   }

   @Override
   public Integer deleteKeyWord(Long keywordId) {
      keyWordJpaRepository.deleteById(keywordId);
      return 1;
   }

   @Override
   public Long getKeywordId(String keywordValue) {
      var keywordId = keyWordJpaRepository.findKeyWordIdByWord(keywordValue);
      if (keywordId == null)
         throw new KeyWordNotFoundException(ExceptionMessage.KEY_WORD_NOT_FOUND);
      return keywordId;
   }

}
