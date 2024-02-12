package us.kanddys.laia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.kanddys.laia.model.ImageProduct;

@Repository
public interface ImageProductJpaRepository extends JpaRepository<ImageProduct, Long> {

   /**
    * Este método se encarga de obtener todas las imágenes pertenecientes a un
    * producto.
    *
    * @param productId
    * @return List<ImageProduct>
    */
   List<ImageProduct> findByProductId(Long productId);
}
