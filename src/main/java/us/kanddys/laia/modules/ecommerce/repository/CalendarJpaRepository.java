package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Calendar;

@Repository
public interface CalendarJpaRepository extends JpaRepository<Calendar, Long> {

   @Query(value = "SELECT type, delay FROM calendars WHERE id = ?1", nativeQuery = true)
   Map<String, Object> findTypeAndDelayByCalendarId(Long calendarId);

}
