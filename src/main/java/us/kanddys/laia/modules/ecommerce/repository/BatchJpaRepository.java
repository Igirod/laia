package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Batch;

@Repository
public interface BatchJpaRepository extends JpaRepository<Batch, Long> {

   @Query(value = "SELECT days FROM batches e WHERE calendar_id = ?1", nativeQuery = true)
   List<Integer> findDaysByCalendarId(Long calendarId);
}
