package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.OrderProduct;
import us.kanddys.laia.modules.ecommerce.model.OrderProductId;

@Repository
public interface OrderProductJpaRepository extends JpaRepository<OrderProduct, OrderProductId> {
   
}
