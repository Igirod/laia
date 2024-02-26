package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.ExceptionDate;
import java.util.List;
import java.util.Date;

@Repository
public interface DateExceptionJpaRepository extends JpaRepository<ExceptionDate, Long> {

   @Query(value = "SELECT CAST(e.date AS CHAR) FROM exceptions_dates e WHERE calendar_id = ?3 AND e.date BETWEEN ?1 AND ?2", nativeQuery = true)
   List<String> findDateExceptionsByCalendarIdRange(Date startDate, Date endDate, Long calendarId);
}
