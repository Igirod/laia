package us.kanddys.laia.modules.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;

@Repository
public class InvoiceCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public List<Invoice> findinvoicesPaginated(Integer page, Long merchantId, Optional<String> userEmail,
         Optional<InvoiceStatus> status) {
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Invoice> cQueryinvoice = cBuilder.createQuery(Invoice.class);
      Root<Invoice> rInvoice = cQueryinvoice.from(Invoice.class);
      cQueryinvoice.where(cBuilder.equal(rInvoice.get("merchantId"), merchantId));
      if (userEmail.isPresent())
         cQueryinvoice.where(cBuilder.equal(rInvoice.get("userEmail"), userEmail.get()));
      if (status.isPresent())
         cQueryinvoice.where(cBuilder.equal(rInvoice.get("status"), status.get()));
      cQueryinvoice.select(rInvoice);
      return entityManager.createQuery(cQueryinvoice).setMaxResults(10)
            .setFirstResult((page - 1) * 10).getResultList();
   }
}