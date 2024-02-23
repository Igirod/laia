package us.kanddys.laia.modules.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProduct;
import us.kanddys.laia.modules.ecommerce.model.Product;
import us.kanddys.laia.modules.ecommerce.model.Utils.TypeFilter;

@Repository
public class ProductCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public List<Product> findProductsByMerchantSlug(String slug, Integer page) {
      List<Predicate> predicates = new ArrayList<>();
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Product> cQueryproduct = cBuilder.createQuery(Product.class);
      Root<Product> rProduct = cQueryproduct.from(Product.class);
      predicates.add(cBuilder.equal(rProduct.get("slug"), slug));
      cQueryproduct.where(predicates.toArray(new Predicate[0]));
      return entityManager.createQuery(cQueryproduct).setMaxResults(10)
            .setFirstResult((page - 1) * 10).getResultList();
   }

   public List<Product> findProductsPaginated(Integer page, Long merchantId, Optional<Integer> status) {
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Product> cQueryproduct = cBuilder.createQuery(Product.class);
      Root<Product> rProduct = cQueryproduct.from(Product.class);
      cQueryproduct.where(cBuilder.equal(rProduct.get("merchantId"), merchantId));
      if (status.isPresent())
         cQueryproduct.where(cBuilder.equal(rProduct.get("status"), status.get()));
      cQueryproduct.select(rProduct);
      return entityManager.createQuery(cQueryproduct).setMaxResults(10)
            .setFirstResult((page - 1) * 10).getResultList();
   }

   public List<Product> findProductsByTypeFilterPaginated(Integer page, TypeFilter typeFilter) {
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Product> cQueryproduct = cBuilder.createQuery(Product.class);
      Root<Product> rProduct = cQueryproduct.from(Product.class);
      switch (typeFilter) {
         case NEW:
            cQueryproduct.orderBy(cBuilder.desc(rProduct.get("createdAt")));
            break;
         case CHEAP:
            cQueryproduct.orderBy(cBuilder.asc(rProduct.get("price")));
            break;
         case EXPENSIVE:
            cQueryproduct.orderBy(cBuilder.desc(rProduct.get("price")));
            break;
         case RELEVANT:
            CriteriaQuery<Long> subquery = cBuilder.createQuery(Long.class);
            Root<InvoiceProduct> subRoot = subquery.from(InvoiceProduct.class);
            subquery.select(subRoot.get("id").get("productId"));
            subquery.groupBy(subRoot.get("id").get("productId"));
            subquery.orderBy(cBuilder.desc(cBuilder.sum(subRoot.get("quantity"))));
            cQueryproduct.select(rProduct);
            Join<Product, InvoiceProduct> productShoppingCartJoin = rProduct.join("invoiceProducts");
            cQueryproduct.groupBy(rProduct.get("id"));
            cQueryproduct.where(rProduct.get("id").in(entityManager.createQuery(subquery)
                  .setFirstResult((page - 1) * 10)
                  .setMaxResults(10)
                  .getResultList()));
            cQueryproduct.orderBy(cBuilder.desc(cBuilder.sum(productShoppingCartJoin.get("quantity"))));
            break;
      }
      return entityManager.createQuery(cQueryproduct).setMaxResults(10)
            .setFirstResult((page - 1) * 10).getResultList();
   }
}
