package us.kanddys.laia.modules.ecommerce.services;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * SellerQuestionProductServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface SellerQuestionProductService {

   /**
    * Este método se encarga de crear una pregunta que un usuario puede hacer a un
    * vendedor perteneciente a un producto.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @param sellerQuestionId
    * @return Long
    */
   public Integer createSellerQuestionProduct(Long productId, Long sellerQuestionId);

   /**
    * Este método se encarga de eliminar una pregunta que un usuario puede hacer a
    * un vendedor perteneciente a un producto.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param productId
    * @param sellerQuestionId
    * @return Integer
    */
   public Integer deleteSellerQuestionProduct(Long productId, Long sellerQuestionId);
}
