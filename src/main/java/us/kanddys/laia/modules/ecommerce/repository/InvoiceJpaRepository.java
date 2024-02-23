package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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

   @Query(value = "UPDATE invoices SET message = :message WHERE id = :invoiceId", nativeQuery = true)
   void updateMessageByInvoiceId(@Param("message") Integer message, @Param("invoiceId") Long invoiceId);

   @Query(value = "UPDATE invoices SET payment_id = :paymentId WHERE id = :invoiceId", nativeQuery = true)
   public Integer updatePaymentByInvoiceId(Long paymentId, Long invoiceId);

   @Query(value = "UPDATE invoices SET note = :note WHERE id = :invoiceId", nativeQuery = true)
   public void updateNoteByInvoiceId(String note, Long invoiceId);

   @Query(value = "UPDATE invoices SET status = :status WHERE id = :invoiceId", nativeQuery = true)
   public void updateStatusByInvoiceId(InvoiceStatus status, Long invoiceId);

   @Query(value = "UPDATE invoices SET voucher = :voucher WHERE id = :invoiceId", nativeQuery = true)
   public void updateVoucherByInvoiceId(String voucher, Long invoiceId);


}
