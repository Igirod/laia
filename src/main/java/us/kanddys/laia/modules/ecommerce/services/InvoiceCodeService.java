package us.kanddys.laia.modules.ecommerce.services;
import us.kanddys.laia.modules.ecommerce.model.Invoice;

/**
 * Esta interfaz contiene las obligaciones que debe implementar la clase
 * InvoiceCodeServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface InvoiceCodeService {

   /**
    * Este método se encarga de generar un código de factura.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceInputDTO
    * @return InvoiceInputDTO
    */
   public Invoice generateInvoiceCode(Invoice invoice);
}
