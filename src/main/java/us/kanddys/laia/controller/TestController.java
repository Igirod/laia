package us.kanddys.laia.controller;

import us.kanddys.laia.exception.IOJavaException;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

   @QueryMapping("getConnection")
   public String getConnection(@Argument String message) {
      throw new IOJavaException(message);
   }

}
