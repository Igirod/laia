package us.kanddys.laia.modules.ecommerce.model.Utils;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;

/**
 * Esta clase representa un conjunto de utilidades para el manejo de facturas.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class InvoiceUtils {
   
   /**
    * Este método se encarga de generar un código de factura.
    *
    * @author Igirod
    * @version 1.0.0
    * @param invoiceDTO
    * @return String
    */
   public static String generateInvoiceCode(Long merchantId) {
      return DateUtils.getCurrentDateString()+"MV"+merchantId;
   }
}
