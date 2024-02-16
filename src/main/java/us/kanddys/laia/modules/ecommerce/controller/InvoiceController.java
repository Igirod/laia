package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;

@Controller
public class InvoiceController {

   @Autowired
   private InvoiceService invoiceService;

   @QueryMapping("findInvoicesByMerchantEmailPaginated")
   public List<InvoiceDTO> getInvoicesByMerchantEmailPaginated(Integer page, String merchantEmail,
         Optional<String> userEmail,
         Optional<InvoiceStatus> status) {
      return invoiceService.findInvoicesByMerchantEmailPaginated(page, merchantEmail, userEmail, status);
   }

   @MutationMapping("createInvoice")
   public InvoiceDTO createInvoice(@Argument InvoiceDTO invoiceDTO) {
      return invoiceService.createInvoice(invoiceDTO);
   }

   @MutationMapping("updateInvoice")
   public InvoiceDTO updateInvoice(@Argument InvoiceDTO invoiceDTO) {
      return invoiceService.updateInvoice(invoiceDTO);
   }

}
