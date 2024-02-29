package us.kanddys.laia.modules.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.modules.ecommerce.model.Address;

@Repository
public interface AddressJpaRepository extends JpaRepository<Address, Long> {

   @Modifying
   @Query(value = "DELETE FROM address_users WHERE id = :addressId", nativeQuery = true)
   Integer deleteAddress(@Param("addressId") Long addressId);
}
