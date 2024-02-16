package us.kanddys.laia.modules.ecommerce.services;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;

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
    * @param invoiceDTO
    * @return InvoiceDTO
    */
   public InvoiceDTO generateInvoiceCode(InvoiceDTO invoiceDTO);
}
