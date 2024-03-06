package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProduct;
import us.kanddys.laia.modules.ecommerce.model.Product;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypeFilter;
import us.kanddys.laia.modules.ecommerce.repository.ProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductRepository;
import us.kanddys.laia.modules.ecommerce.services.ProductService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

/**
 * Esta clase implementa las obligaciones de ProductService.
 * 
 * @author Igirod0
 * @version 1.0.2
 */
@Service
public class ProductServiceImpl implements ProductService {

   @Autowired
   private ProductCriteriaRepository productCriteriaRepository;

   @Autowired
   private ProductRepository productRepository;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Override
   public ProductDTO getProductById(Long productId) {
      try {
         return new ProductDTO(productRepository.findById(productId)
               .orElseThrow(() -> new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND)));
      } catch (ProductNotFoundException e) {
         throw new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }

   @Override
   public List<ProductDTO> getProductsPaginated(Integer page, Long merchantId, Optional<Integer> status) {
      return productCriteriaRepository.findProductsPaginated(page, merchantId, status).stream().map(t -> {
         try {
            return new ProductDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).collect(Collectors.toList());
   }

   @Override
   public List<ProductDTO> getProductsByTypeFilterPaginated(Integer page, TypeFilter typeFilter) {
      return productCriteriaRepository.findProductsByTypeFilterPaginated(page, typeFilter).stream().map(t -> {
         try {
            return new ProductDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).collect(Collectors.toList());
   }

   @Override
   public Integer updateFrontPage(Long productId, MultipartFile image) {
      productRepository.updateFrontPage(productId, firebaseStorageService.uploadFile(image, "frontPages"));
      return 1;
   }

   @Override
   public Integer createProduct(Long merchantId, Optional<String> title, Optional<Double> price,
         Optional<Integer> stock, Optional<Integer> status) {
      try {
         productRepository
               .save(new Product(null, (title.isPresent() ? title.get() : null),
                     (price.isPresent() ? price.get() : null),
                     (stock.isPresent() ? stock.get() : null), null, merchantId,
                     (status.isPresent() ? status.get() : null), DateUtils.getCurrentDate(),
                     new ArrayList<InvoiceProduct>()));
      } catch (Exception e) {
         throw new RuntimeException(e.getMessage());
      }
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateProduct(Long productId, Optional<String> title, Optional<Double> price, Optional<Integer> stock,
         Optional<Integer> status) {
      var product = productRepository.findById(productId);
      if (product.isPresent()) {
         var productToUpdate = product.get();
         title.ifPresent(productToUpdate::setTitle);
         price.ifPresent(productToUpdate::setPrice);
         stock.ifPresent(productToUpdate::setStock);
         status.ifPresent(productToUpdate::setStatus);
         productRepository.save(productToUpdate);
         return 1;
      }
      return 0;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer deleteProduct(Long productId) {
      productRepository.deleteById(productId);
      return 1;
   }
}
