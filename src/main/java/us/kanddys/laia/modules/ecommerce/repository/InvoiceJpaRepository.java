package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;

@Repository
public interface InvoiceJpaRepository extends JpaRepository<Invoice, Long> {

   @Query(value = "SELECT MAX(id) FROM invoices WHERE mer_id = :merchantId AND status IN ('PENDING', 'COMPLETE')", nativeQuery = true)
   public Long findMaxInvoiceIdByMerchantIdAndStatus(@Param("merchantId") Long merchantId);

   @Query(value = "SELECT id from invoices where id = :invoiceId", nativeQuery = true)
   public Long existByInvoiceId(@Param("invoiceId") Long invoiceId);

   public Invoice findInvoiceByUserIdAndMerchantIdAndStatus(Long userId, Long merchantId, InvoiceStatus status);

   @Modifying
   @Query(value = "UPDATE invoices SET message = :message WHERE id = :invoiceId", nativeQuery = true)
   void updateMessageByInvoiceId(@Param("message") String message, @Param("invoiceId") Long invoiceId);

   @Modifying
   @Query(value = "UPDATE invoices SET payment_id = :paymentId WHERE id = :invoiceId", nativeQuery = true)
   public Integer updatePaymentByInvoiceId(Long paymentId, Long invoiceId);

   @Modifying
   @Query(value = "UPDATE invoices SET note = :note WHERE id = :invoiceId", nativeQuery = true)
   public void updateNoteByInvoiceId(String note, Long invoiceId);

   @Modifying
   @Query(value = "UPDATE invoices SET status = :status WHERE id = :invoiceId", nativeQuery = true)
   public void updateStatusByInvoiceId(String status, Long invoiceId);

   @Modifying
   @Query(value = "UPDATE invoices SET voucher = :voucher WHERE id = :invoiceId", nativeQuery = true)
   public void updateVoucherByInvoiceId(String voucher, Long invoiceId);

   @Modifying
   @Query(value = "UPDATE invoices SET total = :total WHERE id = :invoiceId", nativeQuery = true)
   public void updateTotal(Long invoiceId, Double total);

   @Modifying
   @Query(value = "UPDATE invoices SET user_id = :userId WHERE id = :invoiceId", nativeQuery = true)
   public void updateUserId(Long invoiceId, Long userId);

   @Query(value = "SELECT total FROM invoices WHERE id = :invoiceId", nativeQuery = true)
   public Double findTotalById(Long invoiceId);

   @Modifying
   @Query(value = "UPDATE invoices SET address_lat = :lat, address_lng = :lng, address_direction = :direction WHERE id = :invoiceId", nativeQuery = true)
   public void updateAddressByInvoiceId(String direction, String lat, String lng, Long invoiceId);

   @Query(value = "SELECT * FROM invoices WHERE user_id = :userId AND mer_id = :merchantId AND status = :status", nativeQuery = true)
   public Invoice findInvoiceIdByUserIdAndMerchantIdAndStatus(@Param("userId") Long userId,
         @Param("merchantId") Long merchantId, @Param("status") String status);

   @Modifying
   @Query(value = "UPDATE invoices SET reservation = :reservation, batch_id = :batchId WHERE id = :invoiceId", nativeQuery = true)
   public void updateReservationAndBatchIdByInvoiceId(@Param("reservation") String reservation,
         @Param("batchId") Long batchId, @Param("invoiceId") Long invoiceId);

   @Query("SELECT total FROM Invoice WHERE id = :invoiceId")
   public Double findTotalByInvoiceId(@Param("invoiceId") Long invoiceId);

   @Modifying
   @Query(value = "UPDATE invoices SET batch_id = :batchId, payment_id = :paymentId, status = :status WHERE id = :invoiceId", nativeQuery = true)
   public void updateBatchIdAndPaymentIdAndStatusByInvoiceId(@Param("batchId") Long batchId,
         @Param("paymentId") Long paymentId, @Param("status") String status, @Param("invoiceId") Long invoiceId);
}
