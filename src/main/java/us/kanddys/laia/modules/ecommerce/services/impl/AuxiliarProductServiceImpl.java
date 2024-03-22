package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.ArticleImageDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.NewArticleDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.ProductDTO;
import us.kanddys.laia.modules.ecommerce.model.AuxiliarMultipleOptionQuestion;
import us.kanddys.laia.modules.ecommerce.model.AuxiliarProduct;
import us.kanddys.laia.modules.ecommerce.model.AuxiliarProductKeyWord;
import us.kanddys.laia.modules.ecommerce.model.AuxiliarProductMedia;
import us.kanddys.laia.modules.ecommerce.model.ImageProduct;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarMultipleQuestionJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarProductKeyWordJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarProductMediaJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ImageProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.AuxiliarProductService;
import us.kanddys.laia.modules.ecommerce.services.ProductService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

/**
 * Esta clase implementa las obligaciones de la interface
 * AuxiliarProductService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class AuxiliarProductServiceImpl implements AuxiliarProductService {

   @Autowired
   private AuxiliarProductJpaRepository auxiliarProductJpaRepository;

   @Autowired
   private AuxiliarProductMediaJpaRepository auxiliarProductMediaRepository;

   @Autowired
   private ImageProductJpaRepository imageProductJpaRepository;

   @Autowired
   private AuxiliarMultipleQuestionJpaRepository auxiliarMultipleQuestionRepository;

   @Autowired
   private AuxiliarProductKeyWordJpaRepository auxiliarProductKeyWordJpaRepository;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Autowired
   private ProductService productService;

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public NewArticleDTO createAuxiliarProduct(Optional<List<MultipartFile>> medias, Optional<String> title,
         Optional<String> typeOfSale, Optional<String> price, Optional<String> stock, Optional<String> status,
         Optional<String> userId, Optional<String> manufacturingTime, Optional<String> invenstmentNote,
         Optional<String> invenstmentAmount, Optional<String> invenstmentTitle, Optional<String> manufacturingType,
         Optional<String> segmentTitle, Optional<String> segmentDescription, Optional<MultipartFile> segmentMedia,
         Optional<String> hashtagValue, Optional<List<String>> keywords, Optional<String> sellerQuestionValue,
         Optional<String> sellerQuestionType, Optional<String> sellerQuestionLimit,
         Optional<String> sellerQuestionRequired, Optional<String> typeOfPrice,
         Optional<List<String>> sellerQuestionOptions) {
      if (userId.isEmpty()) {
         var auxProductId = auxiliarProductJpaRepository.save(new AuxiliarProduct(null,
               null,
               (userId.isPresent() ? Long.parseLong(userId.get()) : null),
               (title.isPresent() ? title.get() : null), (price.isPresent() ? Double.parseDouble(price.get()) : null),
               (stock.isPresent() ? Integer.parseInt(stock.get()) : null),
               (typeOfSale.isPresent() ? typeOfSale.get() : null),
               (hashtagValue.isPresent() ? hashtagValue.get() : null),
               (manufacturingType.isPresent() ? manufacturingType.get() : null),
               (manufacturingTime.isPresent() ? Integer.parseInt(manufacturingTime.get()) : null),
               (segmentTitle.isPresent() ? segmentTitle.get() : null),
               (segmentDescription.isPresent() ? segmentDescription.get() : null),
               null,
               (invenstmentNote.isPresent() ? invenstmentNote.get() : null),
               (invenstmentAmount.isPresent() ? Double.parseDouble(invenstmentAmount.get()) : null),
               (invenstmentTitle.isPresent() ? invenstmentTitle.get() : null),
               (sellerQuestionValue.isPresent() ? sellerQuestionValue.get() : null),
               (sellerQuestionType.isPresent() ? sellerQuestionType.get() : null),
               (sellerQuestionLimit.isPresent() ? Integer.parseInt(sellerQuestionLimit.get()) : null),
               (sellerQuestionRequired.isPresent() ? Integer.parseInt(sellerQuestionRequired.get()) : null),
               null,
               (typeOfPrice.isPresent() ? typeOfPrice.get() : null))).getAuxProductId();
         ArticleImageDTO frontPage = null;
         if (medias.isPresent()) {
            frontPage = (new ArticleImageDTO(
                  firebaseStorageService.uploadFile(medias.get().get(0),
                        "front-page-product-" + auxProductId.toString(),
                        "frontPages"),
                  "IMAGE"));
            auxiliarProductJpaRepository.updateFrontPage(frontPage.getUrl(), auxProductId);
         }
         if (segmentMedia.isPresent()) {
            auxiliarProductJpaRepository.updateSegmentMedia(
                  firebaseStorageService.uploadFile(segmentMedia.get(), "product-detail" + auxProductId.toString(),
                        "productDetails"),
                  auxProductId);
         }
         if (keywords.isPresent()) {
            auxiliarProductKeyWordJpaRepository.saveAll(keywords.get().stream()
                  .map(keyword -> new AuxiliarProductKeyWord(null, auxProductId, keyword))
                  .collect(Collectors.toList()));
         }
         if (sellerQuestionType.isPresent() && sellerQuestionType.get().equals("MULTIPLE")) {
            sellerQuestionOptions.ifPresent(options -> {
               options.forEach(option -> {
                  // ! Se agrega cada opción de la pregunta multiple.
                  auxiliarMultipleQuestionRepository
                        .save(new AuxiliarMultipleOptionQuestion(null, auxProductId, option));
               });
            });
         }
         return new NewArticleDTO(auxProductId,
               uploadProductMedias(medias, auxProductId, frontPage.getUrl(), frontPage.getType(), 0));
      } else {
         // ! En caso de que se pase el userId por parámetro recurrimos a crear
         // ! directamente el articulo.
         ProductDTO newProductDTO = productService.createProduct(
               (medias.isPresent() ? Optional.of(medias.get().get(0)) : Optional.empty()),
               title,
               typeOfSale, price, stock, status,
               userId, manufacturingTime, invenstmentNote, invenstmentAmount, invenstmentTitle, manufacturingType,
               segmentTitle, segmentDescription, segmentMedia, hashtagValue, keywords, sellerQuestionValue,
               sellerQuestionType, sellerQuestionLimit, sellerQuestionRequired, typeOfPrice,
               sellerQuestionOptions);
         return new NewArticleDTO(newProductDTO.getId(),
               medias.isPresent()
                     ? uploadProductMedias(medias, newProductDTO.getId(), newProductDTO.getFrontPage(), "IMAGE", 1)
                     : null);
      }
   }

   private List<ArticleImageDTO> uploadProductMedias(Optional<List<MultipartFile>> medias, Long auxProductId,
         String frontPageUrl, String frontPageType, Integer operationType) {
      if (operationType == 0) {
         List<ArticleImageDTO> articleProductMedias = new ArrayList<ArticleImageDTO>();
         List<AuxiliarProductMedia> auxiliarProductMedias = new ArrayList<AuxiliarProductMedia>();
         articleProductMedias.add(new ArticleImageDTO(frontPageUrl, frontPageType));
         medias.ifPresent(mediasList -> {
            mediasList.stream()
                  .skip(1) // ! Se salta la primera imagen ya que esta se guarda en el frontPage.
                  .forEach(media -> {
                     var auxiliarProductMedia = new AuxiliarProductMedia(null, auxProductId,
                           firebaseStorageService.uploadFile(media, "image-product-" + auxProductId.toString(),
                                 "imageProducts"),
                           "IMAGE");
                     // ! Por ahora todas las medias son de tipo image.
                     auxiliarProductMedias.add(auxiliarProductMedia);
                     articleProductMedias.add(new ArticleImageDTO(auxiliarProductMedia.getUrl(),
                           auxiliarProductMedia.getType()));
                  });
            auxiliarProductMediaRepository.saveAll(auxiliarProductMedias);
         });
         return articleProductMedias;
      } else {
         List<ArticleImageDTO> articleProductMedias = new ArrayList<ArticleImageDTO>();
         List<ImageProduct> imageProducts = new ArrayList<ImageProduct>();
         articleProductMedias.add(new ArticleImageDTO(frontPageUrl, frontPageType));
         medias.ifPresent(mediasList -> {
            mediasList.stream()
                  .skip(1) // ! Se salta la primera imagen ya que esta se guarda en el frontPage.
                  .forEach(media -> {
                     var imageProduct = new ImageProduct(null, auxProductId,
                           firebaseStorageService.uploadFile(media, "image-product-" + auxProductId.toString(),
                                 "imageProducts"),
                           "IMAGE");
                     // ! Por ahora todas las medias son de tipo image.
                     imageProducts.add(imageProduct);
                     articleProductMedias.add(new ArticleImageDTO(imageProduct.getUrl(),
                           imageProduct.getType()));
                  });
            imageProductJpaRepository.saveAll(imageProducts);
         });
         return articleProductMedias;
      }
   }
}
