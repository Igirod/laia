package us.kanddys.laia.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.model.User;

@Data
@AllArgsConstructor
public class UserDTO {

   @JsonProperty("id")
   private Long id;
   @JsonProperty("name")
   private String name;
   @JsonProperty("mail")
   private String mail;
   @JsonProperty("password")
   private String password;
   @JsonProperty("phone")
   private String phone;

   public UserDTO() {
   };

   public UserDTO(User user) {
      super();
      this.id = (user.getUserId() != null ? user.getUserId() : null);
      this.name = (user.getName() != null ? user.getName() : null);
      this.mail = (user.getMail() != null ? user.getMail() : null);
      this.password = (user.getPassword() != null ? user.getPassword() : null);
      this.phone = (user.getPhone() != null ? user.getPhone() : null);
   }
}
