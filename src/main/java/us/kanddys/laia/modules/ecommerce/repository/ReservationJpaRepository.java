package us.kanddys.laia.modules.ecommerce.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import us.kanddys.laia.modules.ecommerce.model.Reservation;

@Repository
public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {

   @Query(value = "SELECT batch_id, COUNT(*) FROM reservations WHERE batch_id IN :batchIds AND `date` BETWEEN :startDate AND :endDate GROUP BY batch_id", nativeQuery = true)
   List<Object[]> countRecordsByBatchIdsAndDate(List<Long> batchIds, Date startDate, Date endDate);
}
