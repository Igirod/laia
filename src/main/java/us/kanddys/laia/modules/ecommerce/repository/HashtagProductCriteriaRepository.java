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

@Repository
public class HashtagProductCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public Long getHashtagIdsByProductId(Long productId) {
      List<Predicate> predicates = new ArrayList<>();
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Long> cQueryHahstagProduct = cBuilder.createQuery(Long.class);
      Root<Long> rHashtagProduct = cQueryHahstagProduct.from(Long.class);
      predicates.add(cBuilder.equal(rHashtagProduct.get("id").get("productId"), productId));
      cQueryHahstagProduct.where(predicates.toArray(new Predicate[0]));
      return entityManager.createQuery(cQueryHahstagProduct).getSingleResult();
   }

}
