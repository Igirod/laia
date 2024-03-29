package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderPaymentDTO;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceRestController {

   @Autowired
   private InvoiceService invoiceService;

   @RequestMapping(method = { RequestMethod.POST }, value = "/upload", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public OrderPaymentDTO updateOrderVoucher(@RequestPart MultipartFile voucher,
         @RequestPart String invoiceId,
         @RequestPart String paymentId, @RequestPart String date, @RequestPart String batchId,
         @RequestPart String merchantId, @RequestPart String userId, @RequestPart String addressLat,
         @RequestPart String addressLng, @RequestPart String addressDirection) {
      return invoiceService.updateOrderVoucher(voucher, Long.valueOf(invoiceId), Long.valueOf(paymentId), date,
            Long.valueOf(batchId), Long.valueOf(merchantId), Long.valueOf(userId), addressLat, addressLng,
            addressDirection);
   }
}
