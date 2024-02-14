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
import us.kanddys.laia.modules.ecommerce.model.Product;

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

   public List<Product> findProducts(Integer page) {
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Product> cQueryproduct = cBuilder.createQuery(Product.class);
      Root<Product> rProduct = cQueryproduct.from(Product.class);
      cQueryproduct.select(rProduct);
      return entityManager.createQuery(cQueryproduct).setMaxResults(10)
            .setFirstResult((page - 1) * 10).getResultList();
   }
}
