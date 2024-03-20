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
import us.kanddys.laia.modules.ecommerce.controller.dto.ArticleDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.exception.MerchantNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.AuxiliarProduct;
import us.kanddys.laia.modules.ecommerce.model.Product;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypeFilter;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarMultipleQuestionJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarProductMediaJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.CategoryProductService;
import us.kanddys.laia.modules.ecommerce.services.CategoryService;
import us.kanddys.laia.modules.ecommerce.services.HashtagProductService;
import us.kanddys.laia.modules.ecommerce.services.HashtagService;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;
import us.kanddys.laia.modules.ecommerce.services.InvenstmentService;
import us.kanddys.laia.modules.ecommerce.services.KeyWordProductService;
import us.kanddys.laia.modules.ecommerce.services.KeyWordService;
import us.kanddys.laia.modules.ecommerce.services.ManufacturingProductService;
import us.kanddys.laia.modules.ecommerce.services.ProductDetailService;
import us.kanddys.laia.modules.ecommerce.services.ProductService;
import us.kanddys.laia.modules.ecommerce.services.SellerQuestionService;
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
   private ManufacturingProductService manufacturingProductService;

   @Autowired
   private InvenstmentService invenstmentService;

   @Autowired
   private ProductDetailService productDetailService;

   @Autowired
   private HashtagService hashtagService;

   @Autowired
   private HashtagProductService hashtagProductService;

   @Autowired
   private KeyWordService keyWordService;

   @Autowired
   private KeyWordProductService keyWordProductService;

   @Autowired
   private CategoryService categoryService;

   @Autowired
   private CategoryProductService categoryProductService;

   @Autowired
   private ImageProductService imageProductService;

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Autowired
   private SellerQuestionService sellerQuestionService;

   @Autowired
   private AuxiliarProductJpaRepository auxiliarProductJpaRepository;

   @Autowired
   private AuxiliarMultipleQuestionJpaRepository auxiliarMultipleQuestionRepository;

   @Autowired
   private AuxiliarProductMediaJpaRepository auxiliarProductMediaRepository;

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
         Optional<Integer> status, Optional<String> typeOfSale, Optional<String> typeOfPrice) {
      var product = productJpaRepository.findById(productId);
      if (product.isPresent()) {
         var productToUpdate = product.get();
         title.ifPresent(productToUpdate::setTitle);
         price.ifPresent(productToUpdate::setPrice);
         stock.ifPresent(productToUpdate::setStock);
         status.ifPresent(productToUpdate::setStatus);
         typeOfSale.ifPresent(productToUpdate::setTypeOfSale);
         typeOfPrice.ifPresent(productToUpdate::setTypeOfPrice);
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

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Long createProduct(Optional<MultipartFile> frontPage, Optional<String> title,
         Optional<String> typeOfSale, Optional<String> price, Optional<String> stock, Optional<String> status,
         Optional<String> userId, Optional<String> manufacturingTime, Optional<String> invenstmentNote,
         Optional<String> invenstmentAmount, Optional<String> invenstmentTitle, Optional<String> manufacturingType,
         Optional<String> segmentTitle, Optional<String> segmentDescription, Optional<MultipartFile> segmentMedia,
         Optional<String> hashtagValue, Optional<String> keywordValue, Optional<String> sellerQuestionValue,
         Optional<String> sellerQuestionType, Optional<String> sellerQuestionLimit,
         Optional<String> sellerQuestionRequired, Optional<String> categoryTitle, Optional<String> typeOfPrice,
         Optional<List<String>> sellerQuestionOptions) {
      var userid = Long.valueOf(userId.get());
      var merchantId = userJpaRepository.existByUserId(userid);
      Long newProductId = null;
      if (merchantId == null)
         throw new MerchantNotFoundException(ExceptionMessage.MERCHANT_NOT_FOUND);
      else {
         // * Se crea el producto asociado a un merchant.
         try {
            var newProductDTO = createProductAndDTO(
                  new Product(null, (title.isPresent() ? title.get() : null),
                        (price.isPresent() ? Double.valueOf(price.get()) : null),
                        (stock.isPresent() ? Integer.valueOf(stock.get()) : null), null,
                        merchantId, (status.isPresent() ? Integer.valueOf(status.get()) : null),
                        DateUtils.getCurrentDate(), (typeOfSale.isPresent() ? typeOfSale.get() : null),
                        (typeOfPrice.isPresent() ? typeOfPrice.get() : null),
                        new ArrayList<>()),
                  (frontPage.isPresent() ? frontPage.get() : null));
            createProductExtraAtributes(Optional.of(newProductDTO.getId().toString()), manufacturingTime,
                  invenstmentAmount, invenstmentNote, invenstmentTitle, manufacturingType, segmentTitle,
                  segmentDescription, segmentMedia, hashtagValue, keywordValue, sellerQuestionValue,
                  sellerQuestionType, sellerQuestionLimit, sellerQuestionRequired, categoryTitle,
                  sellerQuestionOptions, userid);
            newProductId = newProductDTO.getId();
         } catch (ParseException e) {
            throw new RuntimeException("Error al convertir la fecha");
         }
      }
      return newProductId;
   }

   /**
    * Este método privado tiene la obligación de agregar los atributos extras de un
    * producto.
    *
    * @auhtor Igirod0
    * @version 1.0.0
    * @param productId
    * @param manufacturingTime
    * @param invenstmentAmount
    * @param invenstmentNote
    * @param invenstmentTitle
    * @param manufacturingType
    * @param segmentTitle
    * @param segmentDescription
    * @param segmentMedia
    * @param hashtagValue
    * @param keywordValue
    * @param sellerQuestionValue
    * @param sellerQuestionType
    * @param sellerQuestionLimit
    * @param sellerQuestionRequired
    */
   private void createProductExtraAtributes(Optional<String> productId, Optional<String> manufacturingTime,
         Optional<String> invenstmentAmount, Optional<String> invenstmentNote, Optional<String> invenstmentTitle,
         Optional<String> manufacturingType, Optional<String> segmentTitle, Optional<String> segmentDescription,
         Optional<MultipartFile> segmentMedia, Optional<String> hashtagValue, Optional<String> keywordValue,
         Optional<String> sellerQuestionValue,
         Optional<String> sellerQuestionType, Optional<String> sellerQuestionLimit,
         Optional<String> sellerQuestionRequired, Optional<String> categoryTitle,
         Optional<List<String>> sellerQuestionOptions, Long userId) {
      if (manufacturingTime.isPresent() && manufacturingType.isPresent()) {
         manufacturingProductService.createManufacturingProduct(Long.valueOf(productId.get()),
               manufacturingType, Optional.of(Integer.valueOf(manufacturingTime.get())));
      }
      if (invenstmentAmount.isPresent() || invenstmentNote.isPresent() || invenstmentTitle.isPresent()) {
         invenstmentService.createInvenstment(Long.valueOf(productId.get()),
               Optional.of(Double.valueOf(invenstmentAmount.get())), invenstmentNote, invenstmentTitle);
      }
      if (segmentDescription.isPresent() || segmentMedia.isPresent() || segmentTitle.isPresent()) {
         productDetailService.createProductDetail(segmentTitle, segmentMedia, Long.valueOf(productId.get()),
               segmentDescription);
      }
      if (hashtagValue.isPresent()) {
         var hashtagId = hashtagService.getHashtagIdByValue(hashtagValue.get());
         // * Si no existe el hashtag se crea.
         if (hashtagId == null) {
            hashtagProductService.createHashtagProduct(hashtagService.createHashtag(hashtagValue.get()),
                  Long.valueOf(productId.get()));
         } else {
            hashtagProductService.createHashtagProduct(hashtagId, Long.valueOf(productId.get()));
         }
      }
      if (keywordValue.isPresent()) {
         var keywordId = keyWordService.getKeywordId(keywordValue.get());
         // * Si no existe la keyword se crea.
         if (keywordId == null) {
            keyWordProductService.createKeyWordProduct(Long.valueOf(productId.get()),
                  keyWordService.createKeyWord(keywordValue.get(), userId));
         } else {
            keyWordProductService.createKeyWordProduct(Long.valueOf(productId.get()), keywordId);
         }
      }
      if (sellerQuestionValue.isPresent() && sellerQuestionType.isPresent()) {
         sellerQuestionService.createQuestion(sellerQuestionValue.get(),
               (sellerQuestionRequired.isPresent() ? Optional.of(Integer.valueOf(sellerQuestionRequired.get())) : null),
               sellerQuestionType.get(),
               (sellerQuestionLimit.isPresent() ? Optional.of(Integer.valueOf(sellerQuestionLimit.get())) : null),
               Long.valueOf(productId.get()),
               sellerQuestionOptions);
      }

      if (categoryTitle.isPresent()) {
         var categoryId = categoryService.getCategoryIdByTitle(categoryTitle.get());
         if (categoryId == null) {
            categoryProductService.createCategoryProduct(categoryService.createCategory(categoryTitle.get()),
                  Long.valueOf(productId.get()));
         } else {
            categoryProductService.createCategoryProduct(categoryId, Long.valueOf(productId.get()));
         }
      }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Long createProduct(Optional<Long> userId, Optional<Long> productId, String title, String tStock, Double price,
         Integer stock) {
      if (productId.isPresent() && userId.isPresent()) {
         productJpaRepository.updateMerchantId(productId.get(), userId.get());
         return productId.get();
      }
      if (productId.isEmpty()) {
         Long existUserId = null;
         if (userId.isPresent()) {
            existUserId = userId.get();
         }
         try {
            return productJpaRepository.save(new Product(null, title, price, stock, null, existUserId, 1,
                  DateUtils.getCurrentDate(), null, null, new ArrayList<>())).getId();
         } catch (ParseException e) {
            throw new RuntimeException("Error al convertir la fecha");
         }
      }
      return -1L;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateAdminSellAssociation(Long productId, Long userId) {
      Optional<AuxiliarProduct> auxiliarProduct = auxiliarProductJpaRepository.findById(productId);
      if (auxiliarProduct.isPresent()) {
         Product product = null;
         try {
            product = new Product(null,
                  (auxiliarProduct.get().getTitle() != null ? auxiliarProduct.get().getTitle() : null),
                  (auxiliarProduct.get().getPrice() != null ? auxiliarProduct.get().getPrice() : null),
                  (auxiliarProduct.get().getStock() != null ? auxiliarProduct.get().getStock() : null),
                  (auxiliarProduct.get().getFrontPage() != null ? auxiliarProduct.get().getFrontPage() : null), userId,
                  1, DateUtils.getCurrentDate(),
                  (auxiliarProduct.get().getTypeOfStock() != null ? auxiliarProduct.get().getTypeOfStock() : null),
                  (auxiliarProduct.get().getTypeOfPrice() != null ? auxiliarProduct.get().getTypeOfPrice() : null),
                  new ArrayList<>());
         } catch (ParseException e) {
            throw new RuntimeException("Error al convertir la fecha");
         }
         product = productJpaRepository.save(product);
         createProductExtraAtributes(Optional.of(product.getId().toString()),
               (auxiliarProduct.get().getManufacturingTime() != null
                     ? Optional.of(auxiliarProduct.get().getManufacturingTime().toString())
                     : null),
               (auxiliarProduct.get().getInvenstmentAmount() != null
                     ? Optional.of(auxiliarProduct.get().getInvenstmentAmount().toString())
                     : null),
               (auxiliarProduct.get().getManufacturingTime() != null
                     ? Optional.of(auxiliarProduct.get().getInvenstmentNote().toString())
                     : null),
               (auxiliarProduct.get().getInvenstmentTitle() != null
                     ? Optional.of(auxiliarProduct.get().getInvenstmentTitle().toString())
                     : null),
               (auxiliarProduct.get().getManufacturingType() != null
                     ? Optional.of(auxiliarProduct.get().getManufacturingType().toString())
                     : null),
               (auxiliarProduct.get().getSegmentTitle() != null
                     ? Optional.of(auxiliarProduct.get().getSegmentTitle().toString())
                     : null),
               (auxiliarProduct.get().getSegmentDescription() != null
                     ? Optional.of(auxiliarProduct.get().getSegmentDescription().toString())
                     : null),
               Optional.empty(),
               (auxiliarProduct.get() != null ? Optional.of(auxiliarProduct.get().getHashtag()) : null),
               (auxiliarProduct.get().getKeyword() != null
                     ? Optional.of(auxiliarProduct.get().getKeyword().toString())
                     : null),
               (auxiliarProduct.get().getQuestionTitle() != null
                     ? Optional.of(auxiliarProduct.get().getQuestionTitle().toString())
                     : null),
               (auxiliarProduct.get().getQuestionType() != null
                     ? Optional.of(auxiliarProduct.get().getQuestionType().toString())
                     : null),
               (auxiliarProduct.get().getQuestionLimit() != null
                     ? Optional.of(auxiliarProduct.get().getQuestionLimit().toString())
                     : null),
               (auxiliarProduct.get().getQuestionRequired() != null
                     ? Optional.of(auxiliarProduct.get().getQuestionRequired().toString())
                     : null),
               (auxiliarProduct.get().getCategoryTitle() != null
                     ? Optional.of(auxiliarProduct.get().getCategoryTitle().toString())
                     : null),
               Optional.of(auxiliarMultipleQuestionRepository.findOptionsByProductId(productId)), userId);
         productDetailService.createProductDetailFrontPageString((auxiliarProduct.get().getSegmentTitle() != null
               ? Optional.of(auxiliarProduct.get().getSegmentTitle().toString())
               : null),
               (auxiliarProduct.get().getSegmentMedia() != null
                     ? Optional.of(auxiliarProduct.get().getSegmentMedia().toString())
                     : null),
               productId, (auxiliarProduct.get().getSegmentDescription() != null
                     ? Optional.of(auxiliarProduct.get().getSegmentDescription().toString())
                     : null));
      } else {
         throw new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
      }
      auxiliarMultipleQuestionRepository.deleteOptionsByProductId(productId);
      auxiliarProductMediaRepository.deleteByAuxiliarProductId(productId);
      auxiliarProductJpaRepository.deleteById(productId);
      return 1;
   }

   @Override
   public ArticleDTO getAdminSellProduct(Long id) {
      ProductDTO productDTO = getProductById(id);
      if (productDTO == null)
         throw new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
      ArticleDTO articleDTO = new ArticleDTO();
      articleDTO.setProductId(id);
      articleDTO.setMedias(imageProductService.getImagesProductByProductId(id));
      articleDTO.setInvenstments(invenstmentService.getAdminSellInvenstments(id));
      articleDTO.setManufacturingProduct(manufacturingProductService.getManufacturingByProductId(id));
      articleDTO.setTitle(productDTO.getTitle());
      articleDTO.setPrice(productDTO.getPrice());
      articleDTO.setTypeOfPrice(productDTO.getTypeOfPrice());
      articleDTO.setStock(productDTO.getStock());
      articleDTO.setSegments(productDetailService.getProductDetailsByProductId(id));
      articleDTO.setHashtag(hashtagService.getHashtagsByProductId(id));
      articleDTO.setKeywords(keyWordService.getKeywordsByProductId(id));
      articleDTO.setQuestions(sellerQuestionService.getAdminSellQuestions(id));
      return articleDTO;
   }
}
