package us.kanddys.laia.modules.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.model.SellerQuestionProduct;
import us.kanddys.laia.modules.ecommerce.model.SellerQuestionProductId;
import us.kanddys.laia.modules.ecommerce.repository.SellerQuestionProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.SellerQuestionProductService;

/**
 * Esta clase implementa las obligaciones de la interface
 * SellerQuestionProductService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class SellerQuestionProductImpl implements SellerQuestionProductService {

   @Autowired
   private SellerQuestionProductJpaRepository sellerQuestionProductJpaRepository;

   @Override
   public Integer createSellerQuestionProduct(Long productId, Long sellerQuestionId) {
      sellerQuestionProductJpaRepository
            .save(new SellerQuestionProduct(new SellerQuestionProductId(productId, sellerQuestionId)));
      return 1;
   }

   @Override
   public Integer deleteSellerQuestionProduct(Long productId, Long sellerQuestionId) {
      sellerQuestionProductJpaRepository.deleteById(new SellerQuestionProductId(productId, sellerQuestionId));
      return 1;
   }

}
