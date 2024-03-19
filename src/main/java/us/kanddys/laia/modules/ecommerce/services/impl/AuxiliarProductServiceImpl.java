package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.model.AuxiliarMultipleOptionQuestion;
import us.kanddys.laia.modules.ecommerce.model.AuxiliarProduct;
import us.kanddys.laia.modules.ecommerce.model.AuxiliarProductMedia;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarMultipleQuestionRepository;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.AuxiliarProductMediaRepository;
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
   private AuxiliarProductMediaRepository auxiliarProductMediaRepository;

   @Autowired
   private AuxiliarMultipleQuestionRepository auxiliarMultipleQuestionRepository;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Autowired
   private ProductService productService;

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Long createAuxiliarProduct(Optional<List<MultipartFile>> medias, Optional<String> title,
         Optional<String> typeOfSale, Optional<String> price, Optional<String> stock, Optional<String> status,
         Optional<String> userId, Optional<String> manufacturingTime, Optional<String> invenstmentNote,
         Optional<String> invenstmentAmount, Optional<String> invenstmentTitle, Optional<String> manufacturingType,
         Optional<String> segmentTitle, Optional<String> segmentDescription, Optional<MultipartFile> segmentMedia,
         Optional<String> hashtagValue, Optional<String> keywordValue, Optional<String> sellerQuestionValue,
         Optional<String> sellerQuestionType, Optional<String> sellerQuestionLimit,
         Optional<String> sellerQuestionRequired, Optional<String> categoryTitle, Optional<String> typeOfPrice,
         Optional<List<String>> sellerQuestionOptions) {
      if (userId.isEmpty()) {
         var auxProductId = auxiliarProductJpaRepository.save(new AuxiliarProduct(null,
               (medias.isPresent() ? firebaseStorageService.uploadFile(medias.get().get(0), "frontPages") : null),
               (userId.isPresent() ? Long.parseLong(userId.get()) : null),
               (title.isPresent() ? title.get() : null), (price.isPresent() ? Double.parseDouble(price.get()) : null),
               (stock.isPresent() ? Integer.parseInt(stock.get()) : null),
               (typeOfSale.isPresent() ? typeOfSale.get() : null),
               (hashtagValue.isPresent() ? hashtagValue.get() : null),
               (keywordValue.isPresent() ? keywordValue.get() : null),
               (manufacturingType.isPresent() ? manufacturingType.get() : null),
               (manufacturingTime.isPresent() ? Integer.parseInt(manufacturingTime.get()) : null),
               (segmentTitle.isPresent() ? segmentTitle.get() : null),
               (segmentDescription.isPresent() ? segmentDescription.get() : null),
               (segmentMedia.isPresent() ? firebaseStorageService.uploadFile(segmentMedia.get(), "productDetails")
                     : null),
               (invenstmentNote.isPresent() ? invenstmentNote.get() : null),
               (invenstmentAmount.isPresent() ? Double.parseDouble(invenstmentAmount.get()) : null),
               (invenstmentTitle.isPresent() ? invenstmentTitle.get() : null),
               (sellerQuestionValue.isPresent() ? sellerQuestionValue.get() : null),
               (sellerQuestionType.isPresent() ? sellerQuestionType.get() : null),
               (sellerQuestionLimit.isPresent() ? Integer.parseInt(sellerQuestionLimit.get()) : null),
               (sellerQuestionRequired.isPresent() ? Integer.parseInt(sellerQuestionRequired.get()) : null),
               (categoryTitle.isPresent() ? categoryTitle.get() : null),
               (typeOfPrice.isPresent() ? typeOfPrice.get() : null))).getAuxProductId();
         medias.ifPresent(mediasList -> {
            mediasList.stream()
                  .skip(1) // ! Se salta la primera imagen ya que esta se guarda en el frontPage.
                  .forEach(media -> {
                     var auxiliarProductMedia = new AuxiliarProductMedia(null, auxProductId,
                           firebaseStorageService.uploadFile(media, "imageProducts"));
                     auxiliarProductMediaRepository.save(auxiliarProductMedia);
                  });
         });
         if (sellerQuestionType.isPresent() && sellerQuestionType.get().equals("MULTIPLE")) {
            sellerQuestionOptions.ifPresent(options -> {
               options.forEach(option -> {
                  // ! Se agrega cada opción de la pregunta multiple.
                  auxiliarMultipleQuestionRepository
                        .save(new AuxiliarMultipleOptionQuestion(null, auxProductId, option));
               });
            });
         }
         return auxProductId;
      } else {
         // ! En caso de que se pase el userId por parámetro recurrimos a crear
         // ! directamente el articulo.
         return productService.createProduct((medias.isPresent() ? Optional.of(medias.get().get(0)) : Optional.empty()),
               title,
               typeOfSale, price, stock, status,
               userId, manufacturingTime, invenstmentNote, invenstmentAmount, invenstmentTitle, manufacturingType,
               segmentTitle, segmentDescription, segmentMedia, hashtagValue, keywordValue, sellerQuestionValue,
               sellerQuestionType, sellerQuestionLimit, sellerQuestionRequired, categoryTitle, typeOfPrice,
               sellerQuestionOptions);
      }
   }
}
