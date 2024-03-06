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
import us.kanddys.laia.modules.ecommerce.services.OrderService;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order Rest Controller", description = "Operaciones REST relacionadas a las ordenes.")
public class OrderRestController {

   @Autowired
   private OrderService orderService;

   @Operation(description = "Servicio que tiene la obligación de actualizar el voucher de una orden.")
   @Parameters({
         @Parameter(name = "voucher", description = "Voucher de la orden", required = true, example = "voucher"),
         @Parameter(name = "orderId", description = "Identificador de la orden", required = true, example = "1") })
   @ApiResponse(responseCode = "200", description = "Devuelve un data transfer object de OrderPaymentDTO.")
   @RequestMapping(method = { RequestMethod.PUT }, value = "/update-voucher", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public OrderPaymentDTO updateOrderVoucher(@RequestPart MultipartFile voucher,
         @RequestPart String orderId) {
      return orderService.updateVoucher(voucher, Long.valueOf(orderId));
   }
}
