package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.DisabledDate;

@Repository
public interface DisabledDateJpaRepository extends JpaRepository<DisabledDate, Long> {

   @Query(value = "SELECT CAST(e.date AS CHAR) FROM disabled_dates e WHERE calendar_id = ?3 AND e.date BETWEEN ?1 AND ?2", nativeQuery = true)
   List<String> findDisabledDatesByCalendarIdRange(Date startDate, Date endDate, Long calendarId);

   @Query(value = "SELECT id FROM disabled_dates e WHERE calendar_id = ?1 AND e.date = ?2", nativeQuery = true)
   Optional<Long> existDisabledDateByCalendarIdAndDate(Long calendarId, Date date);

}
