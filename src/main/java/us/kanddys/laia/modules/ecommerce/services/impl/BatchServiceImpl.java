package us.kanddys.laia.modules.ecommerce.services.impl;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
      var dateSplitted = date.split("-");
      Date startDate;
      Date endDate;
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
      return (reservations.isEmpty()) ? batches
            : filterBatches(batches, reservations, date);
   }

   private List<BatchDTO> filterBatches(List<BatchDTO> batches, List<BatchDateDTO> reservations, String date) {
      List<BatchDTO> batchesFiltered = new ArrayList<BatchDTO>();
      for (int i = 0; i < batches.size(); i++) {
         BatchDTO batch = batches.get(i);
         for (BatchDateDTO reservation : reservations) {
            if (reservation.getDate().equals(date)) {
               batchesFiltered.add(batch);
            }
         }
      }
      return batches.stream().filter(batch -> !batchesFiltered.contains(batch)).toList();
   }
}
