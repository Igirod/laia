package us.kanddys.laia.modules.ecommerce.services.impl;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.BatchDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.BatchDateDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.CalendarDay;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.repository.BatchJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ReservationJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.BatchService;

/**
 * Esta clase implementa las obligaciones de la interface BatchService.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Service
public class BatchServiceImpl implements BatchService {

   @Autowired
   private BatchJpaRepository batchJpaRepository;

   @Autowired
   private ReservationJpaRepository reservationJpaRepository;

   @Override
   public List<BatchDTO> getBatchesByCalendarId(Long calendarId, String day, String date,
         Optional<Integer> exceptionalDate) {
      List<BatchDTO> batches;
      List<BatchDateDTO> reservations;
      try {
         batches = (exceptionalDate.isPresent()
               ? batchJpaRepository.findExceptionBatchesByCalendarIdAndDateNotNull(calendarId,
                     DateUtils.convertStringToDateWithoutTime(date)).stream().map(BatchDTO::new).toList()
               : batchJpaRepository
                     .findByCalendarIdAndDaysContainingAndDateIsNull(calendarId, CalendarDay.getDayNumber(day)).stream()
                     .map(batch -> new BatchDTO(batch)).toList());
         var dateSplitted = date.split("-");
         reservations = reservationJpaRepository.countRecordsByBatchIdsAndDate(
               batches.stream().map(BatchDTO::getId).toList(), DateUtils.convertStringToDateWithoutTime(date),
               DateUtils.convertStringToDateWithoutTime(
                     YearMonth.of(Integer.parseInt(dateSplitted[0]), Integer.parseInt(dateSplitted[1])).atEndOfMonth()
                           .toString()))
               .stream().map(BatchDateDTO::new).toList();
      } catch (ParseException e) {
         throw new RuntimeException("Error al convertir la fecha");
      }
      disableDates(reservations, batches);
      return (reservations.isEmpty()) ? batches
            : batches.stream()
                  .filter(batch -> reservations.stream()
                        .anyMatch(reservation -> reservation.getBatchId().equals(batch.getId())
                              && reservation.getCount() <= batch.getLimit()))
                  .collect(Collectors.toList());

      // DESACTIVAR LA FECHA

   }

   /**
    * Desactiva las fechas que ya no tienen disponibilidad.
    * 
    * @param reservations Lista de reservas.
    * @param batches      Lista de lotes.
    * @param date         Fecha.
    */
   private void disableDates(List<BatchDateDTO> reservations, List<BatchDTO> batches) {
      var dates = reservations.stream()
            .filter(reservation -> batches.stream()
                  .anyMatch(batch -> reservation.getBatchId().equals(batch.getId())
                        && reservation.getCount() == batch.getLimit()))
            .collect(Collectors.toList());
      System.out.println("hola");
   }
}
