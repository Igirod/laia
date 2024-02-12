package us.kanddys.laia.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.controller.dto.ProductDTO;
import us.kanddys.laia.exception.ProductNotFoundException;
import us.kanddys.laia.exception.utils.ExcepcionMessage;
import us.kanddys.laia.repository.ProductCriteriaRepository;
import us.kanddys.laia.repository.ProductRepository;
import us.kanddys.laia.services.ProductService;

/**
 * Esta clase implementa la interfaz ProductService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class ProductServiceImpl implements ProductService {

   @Autowired
   private ProductCriteriaRepository productCriteriaRepository;

   @Autowired
   private ProductRepository productRepository;

   @Override
   public List<ProductDTO> getProductsByMerchantSlug(String slug, Integer pagina) {
      return productCriteriaRepository.findProductsByMerchantSlug(slug, pagina).stream().map(ProductDTO::new)
            .collect(Collectors.toList());
   }

   @Override
   public ProductDTO getProductById(Long id) throws ProductNotFoundException {
      return new ProductDTO(productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(ExcepcionMessage.PRODUCT_NOT_FOUND)));
   }
}
