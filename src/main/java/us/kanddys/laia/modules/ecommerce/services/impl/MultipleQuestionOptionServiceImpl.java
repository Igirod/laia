package us.kanddys.laia.modules.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.services.MultipleQuestionOptionService;

/**
 * Esta clase implementa las obligaciones de la interface
 * MultipleQuestionService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class MultipleQuestionOptionServiceImpl implements MultipleQuestionOptionService {

   @Autowired
   private

   @Override public Integer createOption(Long questionId, String option) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'createOption'");
   }

   @Override
   public Integer deleteOption(Long optionId) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'deleteOption'");
   }

   @Override
   public Integer updateOption(Long optionId, String option) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'updateOption'");
   }

}
