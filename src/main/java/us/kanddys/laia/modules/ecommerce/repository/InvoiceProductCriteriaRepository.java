package us.kanddys.laia.modules.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProduct;

@Repository
public class InvoiceProductCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public List<InvoiceProduct> findInvoiceProductsByInvoiceId(Long InvoiceProductId, Integer page) {
      List<Predicate> predicates = new ArrayList<>();
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<InvoiceProduct> cQueryInvoiceProduct = cBuilder.createQuery(InvoiceProduct.class);
      Root<InvoiceProduct> rInvoiceProduct = cQueryInvoiceProduct.from(InvoiceProduct.class);
      predicates.add(cBuilder.equal(rInvoiceProduct.get("id").get("invoiceId"), InvoiceProductId));
      cQueryInvoiceProduct.where(predicates.toArray(new Predicate[0]));
      return entityManager.createQuery(cQueryInvoiceProduct).setMaxResults(10)
            .setFirstResult((page - 1) * 10).getResultList();
   }
}
