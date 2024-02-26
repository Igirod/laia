package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.BatchDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.CalendarDay;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.repository.BatchJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ExceptionDateJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.BatchService;

/**
 * Esta clase implementa las obligaciones de la interface BatchService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class BatchServiceImpl implements BatchService {

   @Autowired
   private BatchJpaRepository batchJpaRepository;

   @Autowired
   private ExceptionDateJpaRepository exceptionDateJpaRepository;

   @Override
   public List<BatchDTO> getBatchesByCalendarId(Long calendarId, String day, String date) {
      if (exceptionDateJpaRepository.findDateByCalendarIdAndDate(DateUtils.convertStringToDate(date), calendarId)
            .isEmpty()) {
         return batchJpaRepository.findByCalendarIdAndDaysContaining(calendarId, CalendarDay.getDayNumber(day)).stream()
               .map(BatchDTO::new).toList();
      }
      else {
         return 
      }
   }
}
