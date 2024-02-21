package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.ShoppingCartProduct;
import us.kanddys.laia.modules.ecommerce.model.ShoppingCartProductId;

@Repository
public interface ShoppingCartProductJpaRepository extends JpaRepository<ShoppingCartProduct, ShoppingCartProductId> {

   @Query("SELECT COUNT(scp) FROM ShoppingCartProduct scp WHERE scp.shoppingCart.id = :shoppingCartId")
   public Integer countByShoppingCartId(Long shoppingCartId);

}
