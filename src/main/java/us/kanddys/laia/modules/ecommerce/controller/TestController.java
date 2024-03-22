package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import us.kanddys.laia.modules.ecommerce.services.AIService;

@RestController
public class TestController {

   @Autowired
   private AIService aiService;

   @GetMapping("get-connection")
   public String getConnection() {
      return "Prueba de conexion exitosa";
   }

   @GetMapping("chatGPT")
   public String askNewQuestion(@RequestParam String question) {
      return aiService.askNewQuestion(question);
   }
}
