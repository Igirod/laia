package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Order;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

   @Modifying
   @Query(value = "UPDATE Orders SET status = ?2 WHERE id = ?1", nativeQuery = true)
   void updateStatus(Long id, String status);

   @Modifying
   @Query(value = "UPDATE Orders SET note = ?2 WHERE id = ?1", nativeQuery = true)
   void updateNote(Long id, String note);
}
