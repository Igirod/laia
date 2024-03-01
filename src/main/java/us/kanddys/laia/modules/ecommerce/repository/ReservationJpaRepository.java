package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import us.kanddys.laia.modules.ecommerce.model.Reservation;

@Repository
public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {

   @Query(value = "SELECT batch_id, COUNT(*), CAST(Date as CHAR) FROM reservations WHERE batch_id IN :batchIds AND `date` BETWEEN :startDate AND :endDate GROUP BY date", nativeQuery = true)
   List<Object[]> countRecordsByBatchIdsAndDate(List<Long> batchIds, Date startDate, Date endDate);

   @Query(value = "SELECT mer_id, CAST(date as CHAR) FROM reservations WHERE batch_id IN :batchIds", nativeQuery = true)
   List<Object[]> findDatesByBatchIds(List<Long> batchIds);

   @Query(value = "SELECT batch_id, COUNT(*), CAST(Date as CHAR) FROM reservations WHERE batch_id = id AND `date` = date GROUP BY date", nativeQuery = true)
   Object[] countRecordsByBatchIdsAndDate(Long batchId, Date date);
}
