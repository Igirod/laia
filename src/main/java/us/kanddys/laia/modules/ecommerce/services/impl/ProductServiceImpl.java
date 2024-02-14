package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.repository.ProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductRepository;
import us.kanddys.laia.modules.ecommerce.services.ProductService;

/**
 * Esta clase implementa las obligaciones de ProductService.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Service
public class ProductServiceImpl implements ProductService {

   @Autowired
   private ProductCriteriaRepository productCriteriaRepository;

   @Autowired
   private ProductRepository productRepository;

   @Override
   public ProductDTO getProductById(Long productId) throws ProductNotFoundException {
      return new ProductDTO(productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND)));
   }

   @Override
   public List<ProductDTO> getProducts(Integer page) {
      return productCriteriaRepository.findProducts(page).stream().map(ProductDTO::new).collect(Collectors.toList());
   }
}
