package us.kanddys.laia.modules.ecommerce.services.impl;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.BatchDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.BatchDateDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CalendarDTO;
import us.kanddys.laia.modules.ecommerce.model.DisabledDate;
import us.kanddys.laia.modules.ecommerce.model.Utils.CalendarDay;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.repository.BatchJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.CalendarJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.DisabledDateJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ReservationJpaRepository;
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
   private DisabledDateJpaRepository disabledDateJpaRepository;

   @Autowired
   private BatchJpaRepository batchJpaRepository;

   @Autowired
   private CalendarJpaRepository calendarJpaRepository;

   @Autowired
   private ReservationJpaRepository reservationJpaRepository;

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public CalendarDTO getCalendarByMerchantId(String year, String month, String day, String dateDayString,
         Long merchantId) {
      List<BatchDTO> batches;
      List<BatchDateDTO> reservations;
      try {
         Map<String, Object> calendar = calendarJpaRepository.findTypeAndDelayAndCalendarIdByMerchantId(merchantId);
         var calendarId = Long.valueOf((Integer) calendar.get("id"));
         Date startDate = DateUtils
               .convertStringToDateWithoutTime(year + "-" + month + "-" + day);
         Date endDate = DateUtils.convertStringToDateWithoutTime(
               YearMonth.of(Integer.parseInt(year), Integer.parseInt(month)).atEndOfMonth().toString());
         var dateDayInt = CalendarDay.getDayNumber(dateDayString);

         batches = batchJpaRepository.findExceptionBatchesByCalendarIdAndDateNotNull(merchantId, startDate).stream()
               .map(BatchDTO::new).toList();

         batches = (!batches.isEmpty()
               ? batches
               : batchJpaRepository
                     .findByCalendarIdAndDaysContainingAndDateIsNull(calendarId, dateDayInt).stream()
                     .map(batch -> new BatchDTO(batch)).toList());

         reservations = reservationJpaRepository.countRecordsByBatchIdsAndDate(
               batches.stream().map(BatchDTO::getId).toList(), startDate, endDate)
               .stream().map(BatchDateDTO::new).toList();

         // * Desactiva las fechas que ya no tienen disponibilidad para realizar pedidos.
         disableDates(reservations, batches, calendarId, dateDayInt, startDate, endDate);

         return new CalendarDTO(calendarId, (Integer) calendar.get("delay"), (String) calendar.get("type"),
               disabledDateJpaRepository.findDisabledDatesByCalendarIdRange(
                     startDate, endDate, calendarId),
               CalendarDay.getDays(batchJpaRepository.findDaysByCalendarId(calendarId)),
               batchJpaRepository.findExceptionBatchesByCalendarIdAndDateNotNull(calendarId,
                     startDate, endDate));

      } catch (ParseException e) {
         throw new RuntimeException("Error al convertir la fecha");
      }
   }

   /**
    * Desactiva las fechas que ya no tienen disponibilidad para realizar pedidos.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param reservations
    * @param batches
    * @param date
    */
   private void disableDates(List<BatchDateDTO> reservations, List<BatchDTO> batches, Long calendarId,
         Integer day, Date startDate, Date endDate) {
      // * Obtiene los batchs que ya no tienen disponibilidad.
      List<Long> disabledBatchIds = reservations.stream()
            .filter(reservation -> batches.stream()
                  .anyMatch(batch -> reservation.getBatchId().equals(batch.getId())
                        && (reservation.getCount() == batch.getLimit() || reservation.getCount() >= batch.getLimit())))
            .map(reservation -> reservation.getBatchId())
            .distinct()
            .collect(Collectors.toList());
      // * Si la fecha se encuentra en las reservaciones.
      if (DateUtils.convertDateToStringList(
            reservationJpaRepository.findDatesByBatchIds(disabledBatchIds).stream().map(DisabledDate::new)
                  .toList().stream().map(DisabledDate::getDate).toList())
            .contains(startDate.toString())) {
         // * Si el numero de batchs deshabilitados es igual al numero de batchs
         // * existentes.
         Integer maxMerchantBatches = batchJpaRepository.findMaxMerchantBatchesByCalendarId(calendarId, day);
         if (maxMerchantBatches.equals(disabledBatchIds.size())) {
            if (disabledDateJpaRepository
                  .existDisabledDateByCalendarIdAndDate(calendarId,
                        startDate)
                  .isEmpty()) {
               disabledDateJpaRepository
                     .save(new DisabledDate(null, calendarId,
                           startDate));
               // * Actualiza las reservaciones.
               reservations = reservationJpaRepository.countRecordsByBatchIdsAndDate(
                     batches.stream().map(BatchDTO::getId).toList(), startDate, endDate)
                     .stream().map(BatchDateDTO::new).toList();
            }
         }
         if (maxMerchantBatches > disabledBatchIds.size()) {
            try {
               Optional<Long> disabledDateID = disabledDateJpaRepository
                     .existDisabledDateByCalendarIdAndDate(calendarId,
                           DateUtils.convertStringToDateWithoutTime(startDate.toString()));
               // * Elimina la fecha deshabilitada solo si esta presente.
               if (disabledDateID.isPresent()) {
                  disabledDateJpaRepository.deleteById(disabledDateID.get());
               }
            } catch (ParseException e) {
               throw new RuntimeException("Error al convertir la fecha");
            }
         }
      }
   }
}
