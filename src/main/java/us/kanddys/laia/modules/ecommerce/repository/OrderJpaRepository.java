package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

   @Query(value = "SELECT MAX(id) FROM orders WHERE mer_id = :merchantId AND status IN ('PENDING', 'COMPLETE')", nativeQuery = true)
   public Long findMaxOrderIdByMerchantIdAndStatus(@Param("merchantId") Long merchantId);

   @Modifying
   @Query(value = "UPDATE invoices SET code = :code WHERE id = :orderId", nativeQuery = true)
   public void updateCodeByOrderId(@Param("code") String code, @Param("orderId") Long orderId);
}
