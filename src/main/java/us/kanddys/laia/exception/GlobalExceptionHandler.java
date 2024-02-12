package us.kanddys.laia.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import graphql.GraphQLException;

/**
 * Esta clase sen encarga de manejar las excepciones generadas por el servidor.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(GraphQLException.class)
   public ResponseEntity<Object> handleGraphQLException(GraphQLException e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
   }

   @ExceptionHandler(ProductNotFoundException.class)
   public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e) {
      return ResponseEntity.notFound().build();
   }
}
