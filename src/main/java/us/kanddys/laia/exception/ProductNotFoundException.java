package us.kanddys.laia.exception;

/**
 * Esta excepción se lanza cuando no se encuentra un producto.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class ProductNotFoundException extends Exception {
   public ProductNotFoundException(String message) {
      super(message);
   }
}
