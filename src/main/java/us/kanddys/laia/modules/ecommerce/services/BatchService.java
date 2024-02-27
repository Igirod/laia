package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

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
    * @param calendarId
    * @param day
    * @param exceptionalDate
    * @return List<BatchDTO>
    */
   public List<BatchDTO> getBatchesByCalendarId(Long calendarId, String day, String date, Optional<Integer> exceptionalDate);
}
