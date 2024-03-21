package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.KeyWordDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.KeyWordInputDTO;
import us.kanddys.laia.modules.ecommerce.exception.ExistingKeyWordException;
import us.kanddys.laia.modules.ecommerce.exception.KeyWordNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.KeyWord;
import us.kanddys.laia.modules.ecommerce.model.KeyWordProduct;
import us.kanddys.laia.modules.ecommerce.model.KeyWordProductId;
import us.kanddys.laia.modules.ecommerce.repository.KeyWordCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.KeyWordJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.KeyWordProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.KeyWordProductJpaRepository;
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

   @Autowired
   private KeyWordProductCriteriaRepository keyWordProductCriteriaRepository;

   @Autowired
   private KeyWordProductJpaRepository keyWordProductJpaRepository;

   @Autowired
   private KeyWordCriteriaRepository keyWordCriteriaRepository;

   @Override
   public Long createKeyWord(String word, Long userId) {
      if (keyWordJpaRepository.findKeyWordIdByWord(word) != null)
         throw new ExistingKeyWordException(ExceptionMessage.EXISTING_KEY_WORD);
      return keyWordJpaRepository.save(new KeyWord(null, word, userId)).getId();
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
      return keyWordJpaRepository.findKeyWordIdByWord(keywordValue);
   }

   @Override
   public List<KeyWordDTO> getKeywordsByProductId(Long productId) {
      return keyWordJpaRepository
            .findAllById(keyWordProductCriteriaRepository.findKeywordsProductsIdsByProductId(productId)).stream()
            .map(KeyWordDTO::new).toList();
   }

   @Override
   public List<String> getKeywordsByValueAndUserid(Long userdId, Optional<String> keyWordValue) {
      return keyWordCriteriaRepository.findKeywordsByUserIdAndValue(userdId, keyWordValue);
   }

   @Override
   public List<KeyWordDTO> updateKeywordsByProductId(Long productId, Long userId, List<KeyWordInputDTO> keyWords) {
      var productKeyWordsWithId = keyWords.stream()
            .filter(keyWord -> keyWord.getId() != null)
            .collect(Collectors.toList());
      var productKeyWordsWithoutId = keyWords.stream()
            .filter(keyWord -> keyWord.getId() == null)
            .collect(Collectors.toList());
      var deletedKeyWordsId = new ArrayList<Long>();
      keyWordJpaRepository
            .findAllById(keyWordProductCriteriaRepository.findKeywordsProductsIdsByProductId(productId))
            .forEach(keyWord -> {
               if (productKeyWordsWithId.stream()
                     .noneMatch(existKeyWord -> existKeyWord.getId().equals(keyWord.getId())))
                  // ! En caso de que las palabras existentes no se encuentren en el
                  // ! en el listado pasado por parametro, estas se eliminan.
                  deletedKeyWordsId.add(keyWord.getId());
            });
      for (var keyWord : deletedKeyWordsId) {
         for (var countKeyword : keyWordProductJpaRepository
               .countKeyWordProductByWordIds(deletedKeyWordsId)) {
            if (countKeyword == 1) {
               keyWordJpaRepository.deleteById(keyWord);
            }
            if (countKeyword > 1) {
               keyWordProductJpaRepository.deleteById(new KeyWordProductId(keyWord, productId));
            }
         }
      }
      if (!productKeyWordsWithoutId.isEmpty()) {
         // * Se insertan las palabras claves que no tienen id.
         var newProductKeywords = keyWordJpaRepository.saveAll(productKeyWordsWithoutId.stream()
               .map(keyWord -> new KeyWord(null, keyWord.getWord(), userId))
               .collect(Collectors.toList()));
         // * Se asocian las nuevas palabras claves al producto.
         keyWordProductJpaRepository
               .saveAll(newProductKeywords.stream()
                     .map(keyWord -> new KeyWordProduct(new KeyWordProductId(keyWord.getId(), productId)))
                     .collect(Collectors.toList()));
      }
      return keyWordJpaRepository
            .findAllById(keyWordProductCriteriaRepository.findKeywordsProductsIdsByProductId(productId)).stream()
            .map(KeyWordDTO::new).toList();
   }
}
