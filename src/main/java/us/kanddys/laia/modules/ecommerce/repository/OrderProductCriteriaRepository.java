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
import us.kanddys.laia.modules.ecommerce.model.OrderProduct;

@Repository
public class OrderProductCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public List<OrderProduct> findOrderProductsByOrderId(Long orderId) {
      List<Predicate> predicates = new ArrayList<>();
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<OrderProduct> cQueryOrderProduct = cBuilder.createQuery(OrderProduct.class);
      Root<OrderProduct> rOrderProduct = cQueryOrderProduct.from(OrderProduct.class);
      predicates.add(cBuilder.equal(rOrderProduct.get("id").get("orderId"), orderId));
      cQueryOrderProduct.where(predicates.toArray(new Predicate[0]));
      return entityManager.createQuery(cQueryOrderProduct).getResultList();
   }
}
