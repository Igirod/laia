package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.ShoppingCart;

@Repository
public interface ShoppingCartJpaRepository extends JpaRepository<ShoppingCart, Long>{
   
   @Query(value = "SELECT id FROM shopping_carts WHERE id = :shoppingCartId", nativeQuery = true)
   public Long existByShoppingCartId(Long shoppingCartId);
}
