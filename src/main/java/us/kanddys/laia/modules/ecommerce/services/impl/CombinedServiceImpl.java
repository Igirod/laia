package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedProductDetailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedShopDTO;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.MerchantNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.User;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.MerchantJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.CombinedService;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;
import us.kanddys.laia.modules.ecommerce.services.ProductDetailService;
import us.kanddys.laia.modules.ecommerce.services.ProductService;

/**
 * Esta clase implementa las obligaciones de la interface CombinedService.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Service
public class CombinedServiceImpl implements CombinedService {

   @Autowired
   private MerchantJpaRepository merchantJpaRepository;

   @Autowired
   private InvoiceJpaRepository invoiceJpaRepository;

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Autowired
   private ProductJpaRepository productJpaRepository;

   @Autowired
   private ProductService productService;

   @Autowired
   private ImageProductService imageProductService;

   @Autowired
   private InvoiceProductJpaRepository invoiceProductJpaRepository;

   @Autowired
   private ProductDetailService productDetailService;

   @Override
   public CombinedShopDTO findCombinedShop(String slug, Optional<Long> userId) {
      Map<String, Object> merchant = merchantJpaRepository.findMerchantIdAndTitle(slug);
      if (merchant == null)
         throw new MerchantNotFoundException(ExceptionMessage.MERCHANT_NOT_FOUND);
      Long merchantId = merchant.get("id") == null ? null : Long.valueOf(merchant.get("id").toString());
      String merchantTitle = (merchant.get("title") == null ? null : merchant.get("title").toString());
      var products = productService.getProductsPaginated(1, merchantId, Optional.of(1));
      Invoice invoice = invoiceIfUserPresent(userId, merchantId);
      return new CombinedShopDTO(merchantId, merchantTitle, products, invoice.getId(),
            invoiceProductJpaRepository.countByInvoiceId(invoice.getId()), invoice.getUserId());
   }

   @Override
   public CombinedProductDTO findCombinedProduct(Long productId, String slug, Optional<Long> userId) {
      Map<String, Object> merchant = merchantJpaRepository.findMerchantIdAndTitle(slug);
      if (merchant == null)
         throw new MerchantNotFoundException(ExceptionMessage.MERCHANT_NOT_FOUND);
      Long merchantId = merchant.get("id") == null ? null : Long.valueOf(merchant.get("id").toString());
      String merchantTitle = (merchant.get("title") == null ? null : merchant.get("title").toString());
      var images = imageProductService.getImagesProductByProductId(productId);
      var details = productDetailService.getProductDetailsByProductId(productId);
      var product = productService.getProductById(productId);
      var invoice = invoiceIfUserPresent(userId, merchantId);
      return new CombinedProductDTO(merchantId, merchantTitle, product.getId(), product.getTitle(), product.getPrice(),
            product.getFrontPage(),
            images, details, product.getStock(), invoice.getId(),
            invoiceProductJpaRepository.countByInvoiceId(invoice.getId()),
            invoice.getUserId());
   }

   /**
    * Este método se encarga de buscar una factura por usuario y comercio, si no
    * existe crea una nueva factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param userId
    * @param merchantId
    * @return Invoice
    */
   private Invoice invoiceIfUserPresent(Optional<Long> userId, Long merchantId) {
      Invoice invoice;
      if (userId.isPresent()) {
         invoice = invoiceJpaRepository.findInvoiceIdByUserIdAndMerchantIdAndStatus(userId.get(), merchantId,
               InvoiceStatus.INITIAL.toString());
         if (invoice == null)
            throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      } else {
         var newInvoice = new Invoice();
         newInvoice.setUserId(userJpaRepository.save(new User()).getId());
         newInvoice.setMerchantId(merchantId);
         invoice = invoiceJpaRepository.save(newInvoice);
      }
      return invoice;
   }

   @Override
   public CombinedProductDetailDTO findCombinedProductDetail(Long productId, Optional<Long> invoiceId) {
      return new CombinedProductDetailDTO(productJpaRepository.findStockByProductId(productId),
            invoiceId.isPresent()
                  ? (invoiceProductJpaRepository.existInvoiceProductByInvoiceIdAndProductId(invoiceId.get(),
                        productId) != null ? 1
                              : 0)
                  : 0,
            imageProductService.getImagesProductByProductId(productId),
            productDetailService.getProductDetailsByProductId(productId));
   }
}
