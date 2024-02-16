package us.kanddys.laia.modules.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.MerchantDTO;
import us.kanddys.laia.modules.ecommerce.exception.MerchantNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.repository.MerchantJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.MerchantService;

/**
 * Esta clase implementa las obligaciones de MerchantService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class MerchantServiceImpl implements MerchantService {

   @Autowired
   private MerchantJpaRepository merchantJpaRepository;

   @Override
   public MerchantDTO findMerchantBySlug(String slug) {
      return merchantJpaRepository.findBySlug(slug).map(MerchantDTO::new)
            .orElseThrow(() -> new MerchantNotFoundException(ExceptionMessage.MERCHANT_NOT_FOUND));
   }

   @Override
   public MerchantDTO findMerchantById(Long merchantId) {
      return merchantJpaRepository.findById(merchantId).map(MerchantDTO::new)
            .orElseThrow(() -> new MerchantNotFoundException(ExceptionMessage.MERCHANT_NOT_FOUND));
   }
}
