package us.kanddys.laia.modules.ecommerce.services.impl;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.CalendarDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.repository.DateExceptionJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.CalendarService;

/**
 * Esta clase implementa las obligaciones de la interface CalendarService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class CalendarServiceImpl implements CalendarService {

   @Autowired
   private DateExceptionJpaRepository dateExceptionJpaRepository;

   @Override
   public CalendarDTO getCalendarByMerchantId(Long merchantId, Integer year, Integer month, Integer number, String day, Long calendarId) {
      Date date;
      try {
         date = DateUtils.convertStringToDate(YearMonth.of(year, month).atEndOfMonth().toString());
         var exceptionDates = dateExceptionJpaRepository.findDateExceptionsByCalendarIdRange(DateUtils.convertStringToDateWithoutTime(year.toString()+month.toString()+number.toString()) , date, calendarId);
      } catch (ParseException e) {
         throw new RuntimeException("Error al convertir la fecha");
      }
   }
   
}
