package us.kanddys.laia.modules.ecommerce.model.Utils;

/**
 * Esta clase contiene los métodos necesarios para realizar el control
 * de stock de un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class ProductCheckStock {
   
   /**
    * Este método estático se encarga de comprobar si hay stock suficiente para
    * calcular el status del producto.
    * 
    * @author Igirod0
    * @version 1.0.0
    */
   public static Integer returnStatusByStock(Integer stock) {
      return (stock != 0) ? 1 : 0;
   }
}
