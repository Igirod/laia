package us.kanddys.laia.modules.ecommerce.services.impl;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.BatchDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.BatchDateDTO;
import us.kanddys.laia.modules.ecommerce.model.DisabledDate;
import us.kanddys.laia.modules.ecommerce.model.Utils.CalendarDay;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.repository.BatchJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.DisabledDateJpaRepository;
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

   @Autowired
   private DisabledDateJpaRepository disabledDateJpaRepository;

   @Override
   public List<BatchDTO> getBatchesByCalendarId(Long calendarId, String day, String date,
         Optional<Integer> exceptionalDate) {
      List<BatchDTO> batches;
      List<BatchDateDTO> reservations;
      var dateSplitted = date.split("-");
      Date endDate;
      Date startDate;
      try {
         startDate = DateUtils.convertStringToDateWithoutTime(date);
         endDate = DateUtils.convertStringToDateWithoutTime(
               YearMonth.of(Integer.parseInt(dateSplitted[0]), Integer.parseInt(dateSplitted[1])).atEndOfMonth()
                     .toString());
      } catch (ParseException e) {
         throw new RuntimeException("Error al convertir la fecha");
      }
      batches = (exceptionalDate.isPresent()
            ? batchJpaRepository.findExceptionBatchesByCalendarIdAndDateNotNull(calendarId,
                  startDate).stream().map(BatchDTO::new).toList()
            : batchJpaRepository
                  .findByCalendarIdAndDaysContainingAndDateIsNull(calendarId, CalendarDay.getDayNumber(day)).stream()
                  .map(batch -> new BatchDTO(batch)).toList());
      reservations = reservationJpaRepository.countRecordsByBatchIdsAndDate(
            batches.stream().map(BatchDTO::getId).toList(), startDate, endDate)
            .stream().map(BatchDateDTO::new).toList();
      disableDates(reservations, batches, date, calendarId, CalendarDay.getDayNumber(day), startDate, endDate);
      return (reservations.isEmpty()) ? batches
            : filterBatches(batches, reservations, date);
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
   private void disableDates(List<BatchDateDTO> reservations, List<BatchDTO> batches, String date, Long calendarId,
         Integer day, Date startDate, Date endDate) {
      List<Long> disabledBatchIds = reservations.stream()
            .filter(reservation -> batches.stream()
                  .anyMatch(batch -> reservation.getBatchId().equals(batch.getId())
                        && (reservation.getCount() == batch.getLimit() || reservation.getCount() >= batch.getLimit())))
            .map(reservation -> reservation.getBatchId())
            .distinct()
            .collect(Collectors.toList());

      if (!DateUtils.convertDateToStringList(
            reservationJpaRepository.findDatesByBatchIds(disabledBatchIds).stream().map(DisabledDate::new)
                  .toList().stream().map(DisabledDate::getDate).toList())
            .contains(date)
            && batchJpaRepository.findMaxMerchantBatchesByCalendarId(calendarId, day).equals(disabledBatchIds.size())) {
         try {
            if (disabledDateJpaRepository
                  .existDisabledDateByCalendarIdAndDate(calendarId, DateUtils.convertStringToDateWithoutTime(date))
                  .isEmpty()) {
               disabledDateJpaRepository
                     .save(new DisabledDate(null, calendarId, DateUtils.convertStringToDateWithoutTime(date)));
               reservations = reservationJpaRepository.countRecordsByBatchIdsAndDate(
                     batches.stream().map(BatchDTO::getId).toList(), startDate, endDate)
                     .stream().map(BatchDateDTO::new).toList();
            }
         } catch (ParseException e) {
            throw new RuntimeException("Error al convertir la fecha");
         }
      }
   }

   private List<BatchDTO> filterBatches(List<BatchDTO> batches, List<BatchDateDTO> reservations, String date) {
      List<BatchDTO> batchesFiltered = batches;
      for (int i = 0; i < batchesFiltered.size(); i++) {
         BatchDTO batch = batchesFiltered.get(i);
         for (BatchDateDTO reservation : reservations) {
            if (batch.getId().equals(reservation.getBatchId()) && batch.getLimit() <= reservation.getCount()
                  && reservation.getDate().equals(date)) {
               batchesFiltered.remove(i);
            }
         }
      }
      return batchesFiltered;
   }
}
