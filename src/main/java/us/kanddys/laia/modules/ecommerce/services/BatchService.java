package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;

import us.kanddys.laia.modules.ecommerce.controller.dto.BatchDTO;

/**
 * Esta clase contiene las obligaciones que debe implementar BatchServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface BatchService {

   /**
    * Este método devuelve los batches agregados por el merchantId.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param merchantId
    * @return List<BatchDTO>
    */
   public List<BatchDTO> getBatchesByCalendarId(Long calendarId, String day);
}
