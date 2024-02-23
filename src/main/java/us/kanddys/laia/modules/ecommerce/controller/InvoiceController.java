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
   public List<InvoiceDTO> getInvoicesByMerchantIdAndOptionalParamsPaginated(@Argument Integer page,
         @Argument Long merchantId,
         @Argument Optional<String> userEmail,
         @Argument Optional<InvoiceStatus> status) {
      return invoiceService.findInvoicesByMerchantIdAndOptionalParamsPaginated(page, merchantId, userEmail, status);
   }

   @MutationMapping("cInvoice")
   public InvoiceDTO createInvoice(@Argument Long merchantId) {
      return invoiceService.createInvoice(merchantId);
   }

   @MutationMapping("uInvoice")
   public InvoiceDTO updateInvoice(@Argument InvoiceInputDTO invoiceInputDTO) {
      return invoiceService.updateInvoice(invoiceInputDTO);
   }

   @QueryMapping("gInvoice")
   public InvoiceDTO getInvoiceByUserIdAndMerchantIdAndStatus(@Argument Long userId, @Argument Long merchantId,
         @Argument InvoiceStatus status) {
      return invoiceService.findInvoiceByUserIdAndMerchantIdAndStatus(userId, merchantId, status);
   }

   @MutationMapping("uInvoiceM")
   public Integer updateInvoiceMessage(@Argument Long invoiceId, @Argument Integer message) {
      return invoiceService.updateInvoiceMessage(invoiceId, message);
   }

   @MutationMapping("uInvoicePayment")
   public Integer updateInvoicePayment(@Argument Long invoiceId, @Argument Long paymentId) {
      return invoiceService.updateInvoicePayment(invoiceId, paymentId);
   }

   @MutationMapping("uInvoiceN")
   public Integer updateInvoiceNote(@Argument Long invoiceId, @Argument String note) {
      return invoiceService.updateInvoiceNote(invoiceId, note);
   }

   @MutationMapping("uInvoiceS")
   public Integer updateInvoiceStatus(@Argument Long invoiceId, @Argument InvoiceStatus status) {
      return invoiceService.updateInvoiceStatus(invoiceId, status);
   }

   @MutationMapping("uInvoiceA")
   public Integer updateInvoiceAddress(@Argument Long invoiceId, @Argument String title, @Argument String direction) {
      return invoiceService.updateInvoiceAddress(invoiceId, title, direction);
   }   
}
