package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

   @Query(value = "SELECT id FROM users WHERE email = :userEmail", nativeQuery = true)
   public Long existByUserEmail(String userEmail);

   @Query(value = "SELECT id FROM users WHERE email = :userEmail AND password = :password", nativeQuery = true)
   public Integer existByUserEmail(String userEmail, String password);

   @Modifying
   @Query(value = "UPDATE users SET email = :email WHERE id = :userId", nativeQuery = true)
   public void updateUserEmail(Long userId, String email);

   @Modifying
   @Query(value = "UPDATE users SET image = :image WHERE id = :userId", nativeQuery = true)
   public void updateUserImage(Long userId, String image);

   @Query(value = "SELECT * FROM users WHERE id = :userId", nativeQuery = true)
   public User findUserById(Long userId);
}
