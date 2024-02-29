package us.kanddys.laia.modules.ecommerce.controller.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase contiene los atributos necesarios para representar un
 * BatchDateDTO.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class BatchDateDTO {
   @JsonProperty
   private Long batchId;
   @JsonProperty
   private Integer count;
   @JsonProperty
   private String date;

   public BatchDateDTO(Object[] data) {
      this.batchId = Long.parseLong(data[0].toString());
      this.count = Integer.parseInt(data[1].toString());
      this.date = (String) data[2];
   }
}
