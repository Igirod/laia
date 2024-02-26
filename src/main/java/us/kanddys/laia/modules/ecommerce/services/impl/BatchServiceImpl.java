package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.BatchDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.CalendarDay;
import us.kanddys.laia.modules.ecommerce.repository.BatchJpaRepository;
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

   @Override
   public List<BatchDTO> getBatchesByCalendarId(Long calendarId, String day) {
      //Analizar si es una fecha excepcional y buscar por id.
      return batchJpaRepository.findByCalendarIdAndDaysContaining(calendarId, CalendarDay.getDayNumber(day)).stream().map(BatchDTO::new).toList();
   }
}
