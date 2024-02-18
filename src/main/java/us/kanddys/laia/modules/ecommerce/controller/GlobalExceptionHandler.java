package us.kanddys.laia.modules.ecommerce.controller;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import graphql.GraphQLError;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceCheckCodeException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.ProductNotFoundException;

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
}