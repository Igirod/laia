package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.text.ParseException;
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
import us.kanddys.laia.modules.ecommerce.exception.MerchantNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Merchant;
import us.kanddys.laia.modules.ecommerce.model.Product;
import us.kanddys.laia.modules.ecommerce.model.User;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypeFilter;
import us.kanddys.laia.modules.ecommerce.repository.MerchantJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
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
   private ProductJpaRepository productJpaRepository;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Autowired
   private MerchantJpaRepository merchantJpaRepository;

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Override
   public ProductDTO getProductById(Long productId) {
      try {
         return new ProductDTO(productJpaRepository.findById(productId)
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
      productJpaRepository.updateFrontPage(productId, firebaseStorageService.uploadFile(image, "frontPages"));
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateProduct(Long productId, Optional<String> title, Optional<Double> price, Optional<Integer> stock,
         Optional<Integer> status, Optional<String> typeOfSale, Optional<Integer> manufacturingTime) {
      var product = productJpaRepository.findById(productId);
      if (product.isPresent()) {
         var productToUpdate = product.get();
         title.ifPresent(productToUpdate::setTitle);
         price.ifPresent(productToUpdate::setPrice);
         stock.ifPresent(productToUpdate::setStock);
         status.ifPresent(productToUpdate::setStatus);
         manufacturingTime.ifPresent(productToUpdate::setManufacturingTime);
         typeOfSale.ifPresent(productToUpdate::setTypeOfSale);
         productJpaRepository.save(productToUpdate);
         return 1;
      }
      return 0;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer deleteProduct(Long productId) {
      productJpaRepository.deleteById(productId);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer createProduct(Optional<MultipartFile> frontPage, Optional<String> productId, Optional<String> title,
         Optional<String> typeOfSale,
         Optional<String> price, Optional<String> stock, Optional<String> status, Optional<String> merchantId,
         Optional<String> manufacturingTime) {
      if (merchantId.isEmpty()) {
         // * Primero se crea el usuario y luego el merchant para asociarle el producto.
         try {
            Long newMerchantId = merchantJpaRepository
                  .save(new Merchant(userJpaRepository.save(new User(true)).getId()))
                  .getId();
            createProductAndDTO(
                  new Product(null, (title.isPresent() ? title.get() : null),
                        (price.isPresent() ? Double.valueOf(price.get()) : null),
                        (stock.isPresent() ? Integer.valueOf(stock.get()) : null), null, newMerchantId,
                        (status.isPresent() ? Integer.valueOf(status.get()) : null), DateUtils.getCurrentDate(),
                        (typeOfSale.isPresent() ? typeOfSale.get() : null),
                        (manufacturingTime.isPresent() ? Integer.valueOf(manufacturingTime.get()) : null),
                        new ArrayList<>()),
                  (frontPage.isPresent() ? frontPage.get() : null));
         } catch (ParseException e) {
            throw new RuntimeException("Error al convertir la fecha");
         }
      } else {
         if (merchantJpaRepository.existByMerchantId(Long.valueOf(merchantId.get())) == null)
            throw new MerchantNotFoundException(ExceptionMessage.MERCHANT_NOT_FOUND);
         if (productId.isEmpty()) {
            // * Se crea el producto asociado a un merchant.
            try {
               createProductAndDTO(
                     new Product(null, (title.isPresent() ? title.get() : null),
                           (price.isPresent() ? Double.valueOf(price.get()) : null),
                           (stock.isPresent() ? Integer.valueOf(stock.get()) : null), null,
                           Long.valueOf(merchantId.get()),
                           (status.isPresent() ? Integer.valueOf(status.get()) : null), DateUtils.getCurrentDate(),
                           (typeOfSale.isPresent() ? typeOfSale.get() : null),
                           (manufacturingTime.isPresent() ? Integer.valueOf(manufacturingTime.get()) : null),
                           new ArrayList<>()),
                     (frontPage.isPresent() ? frontPage.get() : null));
            } catch (ParseException e) {
               throw new RuntimeException("Error al convertir la fecha");
            }
         } else {
            // * Se actualiza el producto.
            if (!productJpaRepository.existsById(Long.valueOf(productId.get())))
               throw new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
            updateProduct(Long.valueOf(productId.get()), title.map(String::toString), price.map(Double::valueOf),
                  stock.map(Integer::valueOf), status.map(Integer::valueOf), typeOfSale.map(String::toString),
                  manufacturingTime.map(Integer::valueOf));
         }
      }
      return 1;
   }

   private ProductDTO createProductAndDTO(Product product, MultipartFile frontPage) {
      try {
         var productDTO = new ProductDTO(productJpaRepository.save(product));
         // ! Carga solo la portada.
         if (frontPage != null)
            updateFrontPage(productDTO.getId(), frontPage);
         return productDTO;
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }
}
