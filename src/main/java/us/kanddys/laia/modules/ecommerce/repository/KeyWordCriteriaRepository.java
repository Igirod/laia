package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

@Repository
public class KeyWordCriteriaRepository {

   @Autowired
   private EntityManager entityManager;

   

}
