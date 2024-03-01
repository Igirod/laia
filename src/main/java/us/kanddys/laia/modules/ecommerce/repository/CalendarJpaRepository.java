package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Calendar;

@Repository
public interface CalendarJpaRepository extends JpaRepository<Calendar, Long> {

   @Query(value = "SELECT type, delay, id FROM calendars WHERE merchant_id = ?1", nativeQuery = true)
   Map<String, Object> findTypeAndDelayAndCalendarIdByMerchantId(Long merchantId);

   @Query(value = "SELECT id FROM calendars WHERE merchant_id = ?1", nativeQuery = true)
   Optional<Long> findCalendarIdByMerchantId(Long merchantId);

}
