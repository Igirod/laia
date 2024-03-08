package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.exception.CategoryNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Category;
import us.kanddys.laia.modules.ecommerce.repository.CategoryJpaRepostory;
import us.kanddys.laia.modules.ecommerce.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

   @Autowired
   private CategoryJpaRepostory categoryJpaRepostory;

   @Override
   public Integer createCategory(String title) {
      categoryJpaRepostory.save(new Category(null, title));
      return 1;
   }

   @Override
   public Integer updateCategory(Long id, Optional<String> title) {
      Optional<Category> category = categoryJpaRepostory.findById(id);
      if (category.isPresent()) {
         var categoryToUpdate = category.get();
         title.ifPresent(categoryToUpdate::setTitle);
         categoryJpaRepostory.save(categoryToUpdate);
      } else {
         throw new CategoryNotFoundException(ExceptionMessage.CATEGORY_NOT_FOUND);
      }
      return 1;
   }

   @Override
   public Integer deleteCategory(Long id) {
      categoryJpaRepostory.deleteById(id);
      return 1;
   }
}
