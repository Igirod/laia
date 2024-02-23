package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.services.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceRestController {

   @Autowired
   private InvoiceService invoiceService;

   @RequestMapping(method = RequestMethod.POST, value = "/upload", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public Integer updateInvoiceVoucher(@RequestPart MultipartFile voucher, @RequestPart String invoiceId) {
      return invoiceService.updateInvoiceVoucher(voucher, Long.valueOf(invoiceId));
      
   }

}
