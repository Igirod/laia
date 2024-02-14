package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDetailDTO;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.repository.ProductDetailJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.ProductDetailService;

/**
 * Esta clase implementa las obligaciones de productDetailService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class ProductDetailServiceImpl implements ProductDetailService {

   @Autowired
   private ProductDetailJpaRepository productDetailJpaRepository;

   @Override
   public List<ProductDetailDTO> getProductDetailsByProductId(Long productId) {
      return productDetailJpaRepository.findProductDetailsByProductId(productId).stream().map(t -> {
         try {
            return new ProductDetailDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      })
            .collect(Collectors.toList());
   }

}
