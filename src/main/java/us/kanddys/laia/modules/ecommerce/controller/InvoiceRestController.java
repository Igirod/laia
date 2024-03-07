package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderPaymentDTO;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;

@RestController
@RequestMapping("/invoices")
@Tag(name = "Invoice Rest Controller", description = "Operaciones REST relacionadas a las facturas.")
public class InvoiceRestController {

   @Autowired
   private InvoiceService invoiceService;

   @Operation(description = "Servicio que tiene la obligación de subir un comprobante de pago asociado a una orden.")
   @Parameters({
         @Parameter(name = "voucher", description = "Comprobante de pago", required = true, example = "voucher"),
         @Parameter(name = "invoiceId", description = "Identificador de la factura", required = true, example = "1"),
         @Parameter(name = "paymentId", description = "Identificador del pago", required = true, example = "1"),
         @Parameter(name = "date", description = "Fecha del pago", required = true, example = "2021-10-10"),
         @Parameter(name = "batchId", description = "Identificador del lote", required = true, example = "1"),
         @Parameter(name = "merchantId", description = "Identificador del comercio", required = true, example = "1"),
         @Parameter(name = "userId", description = "Identificador del usuario", required = true, example = "1"),
         @Parameter(name = "addressLat", description = "Latitud de la dirección", required = true, example = "1"),
         @Parameter(name = "addressLng", description = "Longitud de la dirección", required = true, example = "1"),
         @Parameter(name = "addressDirection", description = "Dirección", required = true, example = "addressDirection")
   })
   @ApiResponse(responseCode = "200", description = "Devuelve el data transfer object de OrderPaymentDTO.")
   @RequestMapping(method = { RequestMethod.POST }, value = "/upload", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public OrderPaymentDTO updateOrderVoucher(@RequestPart MultipartFile voucher,
         @RequestPart String invoiceId,
         @RequestPart String paymentId, @RequestPart String date, @RequestPart String batchId,
         @RequestPart String merchantId, @RequestPart String userId, @RequestPart String addressLat,
         @RequestPart String addressLng, @RequestPart String addressDirection, @RequestPart String reservationType) {
      return invoiceService.updateOrderVoucher(voucher, Long.valueOf(invoiceId), Long.valueOf(paymentId), date,
            Long.valueOf(batchId), Long.valueOf(merchantId), Long.valueOf(userId), addressLat, addressLng,
            addressDirection, reservationType);
   }
}
