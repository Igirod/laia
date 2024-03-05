package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.User;

/**
 * Esta clase representa un data transfer object (DTO) para el usuario.
 *
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class UserDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String name;
   @JsonProperty
   private String lastName;
   @JsonProperty
   private String email;
   @JsonProperty
   private String phone;
   @JsonProperty
   private Integer first;
   @JsonProperty
   private Integer operationStatus;
   @JsonProperty
   private String password;
   @JsonProperty
   private String image;

   public UserDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    */
   public UserDTO(User user, Integer operationStatus) {
      super();
      this.id = (user.getId() != null) ? user.getId() : null;
      this.name = (user.getName() != null) ? user.getName() : null;
      this.lastName = (user.getLastName() != null) ? user.getLastName() : null;
      this.email = (user.getMail() != null) ? user.getMail() : null;
      this.phone = (user.getPhone() != null) ? user.getPhone() : null;
      this.first = (user.getFirst() != null) ? user.getFirst() : null;
      this.password = (user.getPassword() != null) ? user.getPassword() : null;
      this.image = (user.getImage() != null) ? user.getImage() : null;
      this.operationStatus = operationStatus;
   }
}
