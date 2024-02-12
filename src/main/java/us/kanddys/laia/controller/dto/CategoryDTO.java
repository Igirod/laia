package us.kanddys.laia.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.model.Category;

/**
 * Esta clase representa un data transfer object de la entidad Category.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class CategoryDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String title;
   @JsonProperty
   private String image;

   public CategoryDTO() {
   }

   public CategoryDTO(Category category) {
      super();
      this.id = (category.getId() != null) ? category.getId() : null;
      this.title = (category.getTitle() != null) ? category.getTitle() : null;
      this.image = (category.getImage().toString() != null) ? category.getImage().toString() : null;
   }

}
