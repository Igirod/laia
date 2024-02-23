package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceProductDTO;
import us.kanddys.laia.modules.ecommerce.services.InvoiceProductService;

@Controller
public class InvoiceProductController {

   @Autowired
   private InvoiceProductService invoiceProductService;

   @MutationMapping("aProductI")
   public Integer addInvoiceProduct(@Argument Long invoiceId, @Argument Long productId) {
      return invoiceProductService.addInvoiceProduct(invoiceId, productId);
   }

   @MutationMapping("uProductI")
   public Integer updateInvoiceProduct(@Argument Long invoiceId, @Argument Long productId, @Argument Integer quantity) {
      return invoiceProductService.updateInvoiceProduct(invoiceId, productId, quantity);
   }

   @QueryMapping("gIProducts")
   public List<InvoiceProductDTO> findInvoiceProductsByInvoiceId(@Argument Long invoiceId, @Argument Integer page) {
      return invoiceProductService.findInvoiceProductsByInvoiceId(invoiceId, page);
   }
}
