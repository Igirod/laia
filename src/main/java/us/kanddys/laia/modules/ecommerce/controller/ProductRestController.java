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
import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.services.ProductService;

@RestController
@RequestMapping("/product")
@Tag(name = "Product Rest Controller", description = "Operaciones REST relacionadas a los productos.")
public class ProductRestController {

   @Autowired
   private ProductService productService;

   @Operation(description = "Servicio que tiene la obligación de subir la imagen de la portada de un producto.")
   @Parameters({
         @Parameter(name = "image", description = "Imagen de la portada", required = true, example = "image"),
         @Parameter(name = "productId", description = "Identificador del producto", required = true, example = "1") })
   @ApiResponse(responseCode = "1", description = "Devuelve 1 si se actualizó correctamente la imagen de portada.")
   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @RequestMapping(method = { RequestMethod.POST }, value = "/upload", produces = {
         "application/json" }, consumes = { "multipart/form-data" })
   public Integer uploadFrontPage(@RequestPart MultipartFile image, @RequestPart String productId) {
      return productService.updateFrontPage(Long.valueOf(productId), image);
   }
}
