package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.InvoiceProduct;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProductId;

@Repository
public interface InvoiceProductJpaRepository extends JpaRepository<InvoiceProduct, InvoiceProductId> {

   @Query(value = "SELECT COUNT(*) FROM invoices_products invp WHERE invp.invoice_id = :invoiceId", nativeQuery = true)
   public Integer countByInvoiceId(@Param("invoiceId") Long invoiceId);

}
