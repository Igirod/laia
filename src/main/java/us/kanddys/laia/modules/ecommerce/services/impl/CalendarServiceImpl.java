package us.kanddys.laia.modules.ecommerce.services.impl;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.CalendarDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.CalendarDay;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.repository.BatchJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.CalendarJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.DateExceptionJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.DisabledDateJpaRepository;
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

   @Autowired
   private DisabledDateJpaRepository disabledDateJpaRepository;

   @Autowired
   private BatchJpaRepository batchJpaRepository;

   @Autowired
   private CalendarJpaRepository calendarJpaRepository;

   @Transactional(rollbackOn = Exception.class)
   @Override
   public CalendarDTO getCalendarByMerchantId(Integer year, Integer month, Integer day, Long calendarId) {
      CalendarDTO calendarDTO = new CalendarDTO();
      try {
         var startDate = year.toString() + "-" + month.toString() + "-" + day.toString();
         var endDate = DateUtils.convertStringToDateWithoutTime(YearMonth.of(year, month).atEndOfMonth().toString());
         Map<String, Object> calendar = calendarJpaRepository.findTypeAndDelayByCalendarId(calendarId);
         calendarDTO.setDelay((Integer) calendar.get("delay"));
         calendarDTO.setTypeCalendar((String) calendar.get("type"));
         calendarDTO.setDisabledDates(disabledDateJpaRepository.findDateExceptionsByCalendarIdRange(
               DateUtils.convertStringToDateWithoutTime(startDate), endDate, calendarId));
         calendarDTO.setExceptionsDates(dateExceptionJpaRepository.findDateExceptionsByCalendarIdRange(
               DateUtils.convertStringToDateWithoutTime(startDate), endDate, calendarId));
         calendarDTO.setWorkingDays(CalendarDay.getDays(batchJpaRepository.findDaysByCalendarId(calendarId)));
         System.out.println(YearMonth.of(year, month).atEndOfMonth().toString());
         calendar = calendarJpaRepository.findTypeAndDelayByCalendarId(calendarId);
      } catch (ParseException e) {
         throw new RuntimeException("Error al convertir la fecha");
      }
      return calendarDTO;
   }

}
