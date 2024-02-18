package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

   @Query(value = "SELECT id FROM users WHERE user_email = :userEmail", nativeQuery = true)
   public Long existByUserEmail(String userEmail);
}
