package us.kanddys.laia.modules.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceInputDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.InvoiceCodeService;

/**
 * Esta clase implementa las obligaciones de la interface
 * InvoiceCodeService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class InvoiceCodeServiceImpl implements InvoiceCodeService {

   @Autowired
   private InvoiceJpaRepository invoiceJpaRepository;

   @Override
   public InvoiceInputDTO generateInvoiceCode(InvoiceInputDTO invoiceDTO) {
      var maxInvoiceId = (invoiceJpaRepository.findMaxInvoiceIdByMerchantIdAndStatus(invoiceDTO.getMerchantId()));
      invoiceDTO.setCode(DateUtils.getCurrentDateString() + "MV" + invoiceDTO.getMerchantId() + "N"
            + (maxInvoiceId == null ? "0" : (maxInvoiceId + 1L)).toString());
      return invoiceDTO;
   }

}
