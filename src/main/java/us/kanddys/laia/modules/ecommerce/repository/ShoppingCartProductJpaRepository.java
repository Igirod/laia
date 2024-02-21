package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.ShoppingCartProduct;
import us.kanddys.laia.modules.ecommerce.model.ShoppingCartProductId;

@Repository
public interface ShoppingCartProductJpaRepository extends JpaRepository<ShoppingCartProduct, ShoppingCartProductId> {

   @Query(value = "SELECT COUNT(*) FROM shopping_carts_products scp WHERE scp.shopping_cart_id = :shoppingCartId", nativeQuery = true)
   public Integer countByShoppingCartId(@Param("shoppingCartId") Long shoppingCartId);

}
