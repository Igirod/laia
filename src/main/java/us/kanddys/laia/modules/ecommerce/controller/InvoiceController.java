package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceInputDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;

@Controller
public class InvoiceController {

   @Autowired
   private InvoiceService invoiceService;

   @QueryMapping("invoices")
   public List<InvoiceDTO> getInvoicesByMerchantIdAndOptionalParamsPaginated(Integer page, Long merchantId,
         Optional<String> userEmail,
         Optional<InvoiceStatus> status) {
      return invoiceService.findInvoicesByMerchantIdAndOptionalParamsPaginated(page, merchantId, userEmail, status);
   }

   @MutationMapping("cInvoice")
   public InvoiceDTO createInvoice(@Argument InvoiceInputDTO invoiceInputDTO) {
      return invoiceService.createInvoice(invoiceInputDTO);
   }

   @MutationMapping("uInvoice")
   public InvoiceDTO updateInvoice(@Argument InvoiceInputDTO invoiceInputDTO) {
      return invoiceService.updateInvoice(invoiceInputDTO);
   }
}
