package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.model.AImessage;
import us.kanddys.laia.modules.ecommerce.services.AIService;

@Service
public class AIServiceImpl implements AIService {

   @Value("${openai.api.key}")
   private String openAIApiKey;

   @Value("${openai.api.url}")
   private String openAIApiUrl;

   @Override
   public String askNewQuestion(String question) {
      List<AImessage> messages = new ArrayList<AImessage>();
      // messages.add(new AImessage("system",
      // "[Sos un asistente que trabaja en una biblioteca de articulos y tu tarea es
      // interpretar que acción quiere llevar a cabo el usuario], [Cualquier pregunta
      // que no este relacionada a los articulos, respondes: Unicamente respondo
      // preguntas relacionadas a la biblioteca de articulos], [Respuesta debe ser una
      // sola palabra clave], [Respuestas: ORDER-LOW-STOCK, ORDER-HIGHER-STOCK,
      // ORDER-STOCK-BESTSELLERS, ORDER-STOCK-LESSSOLD, ORDER-STOCK-CHEAP,
      // ORDER-STOCK-EXPENSIVE]"));

      StringBuilder sb = new StringBuilder();
      sb.append({"role": "user", "content": "Say this is a test!"})
      
      messages.add(new AImessage("user", "Bajo stock?"));
      messages.add(new AImessage("assistant", "ORDER-LOW-STOCK"));
      System.out.println(messages.toString());
      HttpResponse<String> response = null;
      String requestBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [" + messages.toString()
            + ",{\"role\": \"user\", \"content\": \""
            + question + "\"}], \"temperature\": 0.7}";
      HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
      HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(openAIApiUrl))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + openAIApiKey)
            .build();
      try {
         response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
         System.out.println("Status code: " + response.statusCode());
         System.out.println("Response body: " + response.body());
      } catch (IOException | InterruptedException e) {
         e.printStackTrace();
      }
      return response.body();
   }

}
