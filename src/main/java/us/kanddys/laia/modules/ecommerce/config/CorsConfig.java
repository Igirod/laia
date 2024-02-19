package us.kanddys.laia.modules.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

   @Override
   public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*");
            
      // Permitir solicitudes CORS para la ruta de GraphQL
      registry.addMapping("/graphql")
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("POST") // Por lo general, las solicitudes GraphQL son POST
            .allowedHeaders("*");
   }
}
