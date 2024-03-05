package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceProductInputDTO;
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
   public Integer updateInvoiceProduct(@Argument Long invoiceId, @Argument List<InvoiceProductInputDTO> listInvoiceProducts) {
      return invoiceProductService.updateInvoiceProduct(invoiceId, listInvoiceProducts);
   }

   @QueryMapping("gIProducts")
   public List<InvoiceProductDTO> findInvoiceProductsByInvoiceId(@Argument Long invoiceId, @Argument Integer page, @Argument Optional<Integer> limit) {
      return invoiceProductService.findInvoiceProductsByInvoiceId(invoiceId, page, limit);
   }
}
