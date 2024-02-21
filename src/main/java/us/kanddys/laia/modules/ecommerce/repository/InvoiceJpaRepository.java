package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Invoice;

@Repository
public interface InvoiceJpaRepository extends JpaRepository<Invoice, Long> {

   @Query(value = "SELECT MAX(id) FROM invoices WHERE mer_id = :merchantId AND status IN ('PENDING', 'COMPLETE')", nativeQuery = true)
   public Long findMaxInvoiceIdByMerchantIdAndStatus(@Param("merchantId") Long merchantId);
}
