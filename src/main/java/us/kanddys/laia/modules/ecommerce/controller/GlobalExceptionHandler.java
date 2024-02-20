package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import graphql.GraphQLError;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceCheckCodeException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;

/**
 * Esta clase se encarga de manejar las excepciones que se puedan presentar en
 * el modulo de ecommerce.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

   @GraphQlExceptionHandler
   public GraphQLError handleProductNotFoundException(ProductNotFoundException ex) {
      return GraphQLError.newError().message(ex.getMessage()).build();
   }

   @GraphQlExceptionHandler
   public GraphQLError handleIOExceptionException(IOJavaException ex) {
      return GraphQLError.newError().message(ex.getMessage()).build();
   }

   @GraphQlExceptionHandler
   public GraphQLError handleInvoiceNotFoundException(InvoiceNotFoundException ex) {
      return GraphQLError.newError().message(ex.getMessage()).build();
   }

   @GraphQlExceptionHandler
   public GraphQLError handleInvoiceCheckServiceException(InvoiceCheckCodeException ex) {
      return GraphQLError.newError().message(ex.getMessage()).build();
   }

   @ExceptionHandler(ProductNotFoundException.class)
   public ResponseEntity<String> handleProductNotFoundExceptionRest(ProductNotFoundException ex) {
      return new ResponseEntity<>(ExceptionMessage.PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND);
   }
}