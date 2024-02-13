package us.kanddys.laia.controller;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import graphql.GraphQLError;
import us.kanddys.laia.exception.IOJavaException;
import us.kanddys.laia.exception.ProductNotFoundException;

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
}