package us.kanddys.laia.modules.ecommerce.services;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceInputDTO;

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
    * @param invoiceInputDTO
    * @return InvoiceInputDTO
    */
   public InvoiceInputDTO generateInvoiceCode(InvoiceInputDTO invoiceDTO);
}
