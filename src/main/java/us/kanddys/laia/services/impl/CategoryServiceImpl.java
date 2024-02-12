package us.kanddys.laia.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.controller.dto.CategoryDTO;
import us.kanddys.laia.repository.CategoryCriteriaRepository;
import us.kanddys.laia.services.CategoryService;

/**
 * Esta clase implementa la interfaz CategoryService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

   @Autowired
   private CategoryCriteriaRepository categoryCriteriaRepository;

   @Override
   public List<CategoryDTO> getCategories(Integer pagina) {
      return categoryCriteriaRepository.findCategories(pagina).stream().map(CategoryDTO::new)
            .collect(Collectors.toList());
   }
}
