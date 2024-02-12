package us.kanddys.laia.services;

import java.util.List;

import us.kanddys.laia.controller.dto.CategoryDTO;

/**
 * Esta interfaz contiene las obligaciones que debe implementar la
 * clase CategoryServiceImpl.
 */
public interface CategoryService {

   public List<CategoryDTO> getCategories(Integer pagina);
}
