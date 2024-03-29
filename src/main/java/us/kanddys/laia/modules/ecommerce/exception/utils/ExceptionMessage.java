package us.kanddys.laia.modules.ecommerce.exception.utils;

/**
 * Esta clase contiene todos los mensajes de error que se pueden lanzar.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class ExceptionMessage {
   
   public static final String PRODUCT_NOT_FOUND = "El producto buscado no existe.";
   public static final String MERCHANT_NOT_FOUND = "El comerciante buscado no existe.";
   public static final String INVOICE_NOT_FOUND = "La factura buscada no existe.";
   public static final String INOICE_CHECK_SERVICE = "La factura contiene datos invalidos. Revise la informacion enviada.";
   public static final String PRODUCT_CHECK_STOCK_LIMITED = "El stock del producto no es suficiente para realizar la compra.";
   public static final String ADDRESS_NOT_FOUND = "La direccion buscada no existe.";
}
