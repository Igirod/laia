package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

   @GetMapping("get-connection")
   public String getConnection() {
      return "Prueba de conexion exitosa";
   }
}
