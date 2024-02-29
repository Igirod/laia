package us.kanddys.laia.modules.ecommerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import us.kanddys.laia.modules.ecommerce.model.Merchant;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypeShipment;

/**
 * Esta clase representa un data transfer object (DTO) de Merchant.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Data
@AllArgsConstructor
public class MerchantDTO {
   @JsonProperty
   private Long id;
   @JsonProperty
   private String email;
   @JsonProperty
   private String slug;
   @JsonProperty
   private String message;
   @JsonProperty
   private TypeShipment typeShipment;
   @JsonProperty
   private String title;
   @JsonProperty
   private String image;
   @JsonProperty
   private String address;

   public MerchantDTO() {
   }

   /**
    * Constructor personalizado utilizado en diferentes servicios.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param merchant
    */
   public MerchantDTO(Merchant merchant) {
      super();
      this.id = (merchant.getId() == null) ? null : merchant.getId();
      this.email = (merchant.getEmail() == null) ? null : merchant.getEmail();
      this.slug = (merchant.getSlug() == null) ? null : merchant.getSlug();
      this.message = (merchant.getMessage() == null) ? null : merchant.getMessage();
      this.typeShipment = (merchant.getTypeShipment() == null) ? null : merchant.getTypeShipment();
      this.title = (merchant.getTitle() == null) ? null : merchant.getTitle();
      this.image = (merchant.getImage() == null) ? null : merchant.getImage();
      this.address = (merchant.getAddress() == null) ? null : merchant.getAddress();
   }
}
