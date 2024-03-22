package us.kanddys.laia.modules.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AImessage {
   private String role;
   private String content;

   public AImessage() {
   }
}
