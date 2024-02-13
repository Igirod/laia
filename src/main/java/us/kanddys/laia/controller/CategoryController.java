package us.kanddys.laia.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.controller.dto.CategoryDTO;
import us.kanddys.laia.services.CategoryService;

@Controller
public class CategoryController {

   @Autowired
   private CategoryService categoryService;

   @QueryMapping("findCategories")
   public List<CategoryDTO> getCategories(@Argument Integer pagina) {
      return categoryService.getCategories(pagina);
   }
}
