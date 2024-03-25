package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.kanddys.laia.modules.ecommerce.services.AIService;

@Service
public class AIServiceImpl implements AIService {

   @Value("${openai.api.key}")
   private String openAIApiKey;

   @Value("${openai.api.url}")
   private String openAIApiUrl;

   @Override
   public String askNewQuestion(String question) {
      HttpResponse<String> response = null;
      String requestBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \""
            + "[You are an assistant working in an article library and your task is to interpret what action the user wants to perform], [Any question that is not related to the articles, you answer: I only answer questions related to the article library], [Answer: one or more keywords depending on the user's intention], [Answers: ORDER-LOW-STOCK, ORDER-HIGH-STOCK, ORDER-STOCK-BESTSELLERS, ORDER-STOCK-LESSSOLD, ORDER-STOCK-CHEAP, ORDER-STOCK-EXPENSIVE]]"
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
