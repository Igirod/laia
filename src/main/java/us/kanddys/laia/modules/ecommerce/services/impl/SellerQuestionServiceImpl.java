package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.SellerQuestionDTO;
import us.kanddys.laia.modules.ecommerce.exception.SellerQuestionNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.SellerQuestion;
import us.kanddys.laia.modules.ecommerce.model.SellerQuestionMultipleOption;
import us.kanddys.laia.modules.ecommerce.repository.SellerQuestionJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.SellerQuestionMultipleOptionJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.SellerQuestionService;

/**
 * Esta clase implementa las obligaciones definidas en la interfaz
 * SellerQuestionService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class SellerQuestionServiceImpl implements SellerQuestionService {

   @Autowired
   private SellerQuestionJpaRepository sellerQuestionJpaRepository;

   @Autowired
   private SellerQuestionMultipleOptionJpaRepository sellerQuestionMultipleOptionJpaRepository;

   @Override
   public Long createQuestion(String question, Optional<Integer> required, String type,
         Optional<Integer> limit, Long productId, Optional<List<String>> options) {
      var sellerQuestionId = sellerQuestionJpaRepository
            .save(new SellerQuestion(null, question, required.orElse(null), type, limit.orElse(null),
                  productId))
            .getId();
      if (type.equals("MULTIPLE")) {
         if (limit.isEmpty())
            throw new IllegalArgumentException(ExceptionMessage.LIMIT_REQUIRED);
         if (options.isEmpty())
            throw new IllegalArgumentException(ExceptionMessage.OPTIONS_REQUIRED);
         sellerQuestionMultipleOptionJpaRepository.saveAll(options.get().stream()
               .map(option -> new SellerQuestionMultipleOption(null, sellerQuestionId, option))
               .toList());
      }
      return sellerQuestionId;
   }

   @Override
   public Integer deleteQuestion(Long id) {
      sellerQuestionJpaRepository.deleteById(id);
      return 1;
   }

   @Override
   public Integer updateQuestion(Long questionId, Optional<String> question, Optional<String> type,
         Optional<Integer> limit, Optional<Integer> required) {
      var existSellerQuestion = sellerQuestionJpaRepository.findById(questionId);
      if (existSellerQuestion.isEmpty())
         throw new SellerQuestionNotFoundException(ExceptionMessage.SELLER_QUESTION_NOT_FOUND);
      if (question != null) {
         var sellerQuestionToUpdate = existSellerQuestion.get();
         sellerQuestionToUpdate.setQuestion(question.get());
         sellerQuestionToUpdate.setType(type.orElse(null));
         sellerQuestionToUpdate.setLimit(limit.orElse(null));
         sellerQuestionToUpdate.setRequired(required.orElse(null));
         sellerQuestionJpaRepository.save(sellerQuestionToUpdate);
      } else {
         deleteQuestion(questionId);
      }
      return 1;
   }

   @Override
   public Long getQuestionIdByQuestionAndType(String question, String type) {
      return sellerQuestionJpaRepository.findQuestionIdByQuestionAndType(question, type);
   }

   @Override
   public SellerQuestionDTO getQuestionByQuestionAndType(String question, String type) {
      return new SellerQuestionDTO(sellerQuestionJpaRepository.findQuestionByQuestionAndType(question, type));
   }

}
