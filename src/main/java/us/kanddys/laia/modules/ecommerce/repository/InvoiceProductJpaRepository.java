package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.InvoiceProduct;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProductId;

@Repository
public interface InvoiceProductJpaRepository extends JpaRepository<InvoiceProduct, InvoiceProductId> {

   @Query(value = "SELECT COUNT(*) FROM invoices_products invp WHERE invp.invoice_id = :invoiceId", nativeQuery = true)
   public Integer countByInvoiceId(@Param("invoiceId") Long invoiceId);

   @Query(value = "SELECT product_id FROM invoices_products invp WHERE invp.invoice_id = :invoiceId", nativeQuery = true)
   public List<Long> findAllProductsIdByProductId(@Param("invoiceId") Long invoiceId);

   @Modifying
   @Query(value = "UPDATE invoices_products invp SET invp.quantity = :quantity WHERE invp.invoice_id = :invoiceId AND invp.product_id = :productId", nativeQuery = true)
   public Integer updateQuantityByInvoiceIdAndProductId(@Param("invoiceId") Long invoiceId,
         @Param("productId") Long productId, @Param("quantity") Integer quantity);

   @Query(value = "SELECT product_id FROM invoices_products invp WHERE invp.invoice_id = :invoiceId AND invp.product_id = :productId", nativeQuery = true)
   public Long existInvoiceProductByInvoiceIdAndProductId(@Param("invoiceId") Long invoiceId, @Param("productId") Long productId);
}
