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
            + "[Sos un asistente amigable que trabaja en una biblioteca de artículos y tu tarea es interpretar qué acción quiere llevar a cabo el usuario. Analizás palabra por palabra la oración para brindar una respuesta correcta, teniendo en cuenta la ubicación de las órdenes y la intención del usuario], [Cualquier pregunta que no esté relacionada a los artículos, respondes: Únicamente respondo preguntas relacionadas a la biblioteca de articulos], [Respuesta: una o más palabras claves según la intención del usuario separadas por coma], [Respuestas: ORDER-STOCK-LOW-QUANTITY (Ordenar artículos por cantidad ascendente, ejemplo: ordenar artículos por menor cantidad), ORDER-STOCK-HIGH-QUANTITY (Ordenar artículos por cantidad descendente, ejemplo: ordenar artículos por mayor cantidad), ORDER-BESTSELLERS (Ordenar artículos por ventas descendente), ORDER-LESSSOLD (Ordenar artículos por ventas ascendente), ORDER-PRICE-CHEAP (Ordenar artículos por precio ascendente), ORDER-PRICE-EXPENSIVE (Ordenar artículos por precio descendente), CREATE-ARTICLE (Crear un artículo), DELETE-ARTICLE (Eliminar un artículo), UPDATE-ARTICLE (Modificar un artículo)]"
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
