package us.kanddys.laia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import us.kanddys.laia.controller.dto.CategoryDTO;
import us.kanddys.laia.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

   @Autowired
   private CategoryService categoryService;

   @QueryMapping("findCategories")
   public List<CategoryDTO> getCategories(@Argument Integer pagina) {
      return categoryService.getCategories(pagina);
   }
}
