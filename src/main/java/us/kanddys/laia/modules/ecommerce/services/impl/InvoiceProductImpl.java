package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceProductDTO;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProduct;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProductId;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.InvoiceProductService;
import us.kanddys.laia.modules.ecommerce.services.check.InvoiceCheckService;

/**
 * Esta clase implementa las obligaciones de la interface
 * InvoiceProductService.
 * 
 * @author Igirod0
 * @version 1.0.0
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

   @Override
   public Integer addInvoiceProduct(@Argument Long invoiceId, @Argument Long productId) {
      if (invoiceJpaRepository.existByInvoiceId(invoiceId) == null)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      invoiceProductJpaRepository.save(new InvoiceProduct(invoiceId, productId));
      return 1;
   }

   @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceProduct(Long invoiceId, Long productId, Integer quantity) {
      if (quantity == 0) {
         invoiceProductJpaRepository.deleteById(new InvoiceProductId(invoiceId, productId));
      } else {
         var invoiceTotal = invoiceJpaRepository.findTotalById(invoiceId);
         var invoiceProduct = invoiceProductJpaRepository.findById(new InvoiceProductId(invoiceId, productId));
         if (invoiceProduct.isEmpty())
            throw new InvoiceProductNotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND);
         invoiceProduct.get().setQuantity(quantity);
         invoiceProductJpaRepository.save(invoiceProduct.get());
         invoiceCheckService.updateTotal(invoiceId,
               (invoiceTotal == 0 ? 0 : invoiceTotal)
                     + invoiceProduct.get().getQuantity() * invoiceProduct.get().getProduct().getPrice());
      }
      return 1;
   }

   @Override
   public List<InvoiceProductDTO> findInvoiceProductsByInvoiceId(Long invoiceId, Integer page) {
      return invoiceProductCriteriaQueryRepository.findInvoiceProductsByInvoiceId(invoiceId, page).stream()
            .map(InvoiceProductDTO::new)
            .toList();
   }
}