package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceProductInputDTO;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProduct;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProductId;
import us.kanddys.laia.modules.ecommerce.model.Product;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.InvoiceProductService;
import us.kanddys.laia.modules.ecommerce.services.check.InvoiceCheckService;

/**
 * Esta clase implementa las obligaciones de la interface
 * InvoiceProductService.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Service
public class InvoiceProductImpl implements InvoiceProductService {

   @Autowired
   private InvoiceJpaRepository invoiceJpaRepository;

   @Autowired
   private InvoiceProductJpaRepository invoiceProductJpaRepository;

   @Autowired
   private InvoiceProductCriteriaRepository invoiceProductCriteriaQueryRepository;

   @Autowired
   private InvoiceCheckService invoiceCheckService;

   @Autowired
   private ProductJpaRepository productJpaRepository;

   @Override
   public Integer addInvoiceProduct(@Argument Long invoiceId, @Argument Long productId) {
      if (invoiceProductJpaRepository.findById(new InvoiceProductId(invoiceId, productId)).isPresent()) {
         return 1;
      } else {
         if (invoiceJpaRepository.existByInvoiceId(invoiceId) == null)
            throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
         Optional<Product> product = productJpaRepository.findById(productId);
         if (product.isEmpty())
            throw new ProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
         invoiceProductJpaRepository.save(new InvoiceProduct(invoiceId, productId, product.get()));
         invoiceCheckService.updateTotal(invoiceId,
               invoiceJpaRepository.findTotalById(invoiceId) + invoiceProductJpaRepository
                     .findById(new InvoiceProductId(invoiceId, productId)).get().getProduct().getPrice() * 1);
         return 1;
      }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceProduct(Long invoiceId, List<InvoiceProductInputDTO> listInvoiceProducts) {
      var listInvoiceProductsInputIds = listInvoiceProducts.stream().map(InvoiceProductInputDTO::getProductId).toList();
      invoiceProductJpaRepository.findAllProductsIdByProductId(invoiceId).stream()
            .filter(productId -> !listInvoiceProductsInputIds.contains(productId))
            .forEach(productId -> invoiceProductJpaRepository.deleteById(new InvoiceProductId(invoiceId, productId)));
      listInvoiceProducts.stream()
            .forEach(invoiceInputDTO -> invoiceProductJpaRepository.updateQuantityByInvoiceIdAndProductId(
                  invoiceId,
                  invoiceInputDTO.getProductId(),
                  invoiceInputDTO.getQuantity()));
      invoiceCheckService.updateTotal(invoiceId,
            invoiceProductJpaRepository.findAllById(listInvoiceProductsInputIds.stream()
                  .map(invoiceProductInputId -> new InvoiceProductId(invoiceId, invoiceProductInputId))
                  .toList()).stream()
                  .mapToDouble(invoiceProduct -> invoiceProduct.getProduct().getPrice() * invoiceProduct.getQuantity())
                  .sum());
      return 1;
   }

   @Override
   public List<InvoiceProductDTO> findInvoiceProductsByInvoiceId(Long invoiceId, Integer page,
         Optional<Integer> limit) {
      return invoiceProductCriteriaQueryRepository.findInvoiceProductsByInvoiceId(invoiceId, page, limit).stream()
            .map(InvoiceProductDTO::new).toList();
   }
}
