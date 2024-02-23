package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedShopDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.CombinedService;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;
import us.kanddys.laia.modules.ecommerce.services.MerchantService;
import us.kanddys.laia.modules.ecommerce.services.ProductService;

@Service
public class CombinedServiceImpl implements CombinedService {

   @Autowired
   private MerchantService merchantService;

   @Autowired
   private ProductService productService;

   @Autowired
   private ImageProductService imageProductService;

   @Autowired
   private InvoiceService invoiceService;

   @Autowired
   private InvoiceProductJpaRepository invoiceProductJpaRepository;

   @Override
   public CombinedShopDTO findCombinedShop(String slug, Optional<Long> userId) {
      var merchant = merchantService.findMerchantBySlug(slug);
      var products = productService.getProductsPaginated(1, merchant.getId(), Optional.of(1));
      InvoiceDTO invoice;
      if (userId.isPresent()) {
         invoice = invoiceService.findInvoiceByUserIdAndMerchantIdAndStatus(userId.get(), merchant.getId(),
               InvoiceStatus.INITIAL);
      } else {
         invoice = invoiceService.createInvoice(merchant.getId());
      }
      return new CombinedShopDTO(merchant.getId(), merchant.getTitle(), products, invoice.getId(),
            invoiceProductJpaRepository.countByInvoiceId(invoice.getId()));
   }

   @Override
   public CombinedProductDTO findCombinedProduct(Long productId, String slug, Optional<Long> userId) {
      var merchant = merchantService.findMerchantBySlug(slug);
      var images = imageProductService.findImagesProductByProductId(productId);
      var product = productService.getProductById(productId);
      InvoiceDTO invoice;
      if (userId.isPresent()) {
         invoice = invoiceService.findInvoiceByUserIdAndMerchantIdAndStatus(userId.get(), merchant.getId(),
               InvoiceStatus.INITIAL);
      } else {
         invoice = invoiceService.createInvoice(merchant.getId());
      }
      return new CombinedProductDTO(merchant.getId(), merchant.getTitle(), product.getId(), product.getTitle(),
            product.getPrice(), images, product.getStock(), invoice.getId(),
            invoiceProductJpaRepository.countByInvoiceId(invoice.getId()));
   }
}
