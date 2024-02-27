package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Batch;

@Repository
public interface BatchJpaRepository extends JpaRepository<Batch, Long> {

   @Query(value = "SELECT days FROM batches e WHERE calendar_id = ?1", nativeQuery = true)
   List<Integer> findDaysByCalendarId(Long calendarId);

   @Query(value = "SELECT * FROM batches WHERE calendar_id = ?1 AND days LIKE %?2% AND date IS NULL", nativeQuery = true)
   List<Batch> findByCalendarIdAndDaysContainingAndDateIsNull(Long calendarId, Integer days);

   @Query(value = "SELECT CAST(date as CHAR) FROM batches WHERE calendar_id = ?1 AND date BETWEEN ?2 AND ?3 AND date IS NOT NULL", nativeQuery = true)
   List<String> findExceptionBatchesByCalendarIdAndDateNotNull(Long calendarId, Date startDate, Date endDate);

   @Query(value = "SELECT * FROM batches WHERE calendar_id = ?1 AND date = ?2 AND date IS NOT NULL", nativeQuery = true)
   List<Batch> findExceptionBatchesByCalendarIdAndDateNotNull(Long calendarId, Date date);
}
