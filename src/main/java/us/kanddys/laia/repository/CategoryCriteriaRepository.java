package us.kanddys.laia.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import us.kanddys.laia.model.Category;

@Repository
public class CategoryCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   public List<Category> findCategories(Integer pagina) {
      List<Predicate> predicates = new ArrayList<>();
      CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<Category> cQueryproduct = cBuilder.createQuery(Category.class);
      cQueryproduct.where(predicates.toArray(new Predicate[0]));
      return entityManager.createQuery(cQueryproduct).setMaxResults(10)
            .setFirstResult((pagina - 1) * 10).getResultList();
   }
}
