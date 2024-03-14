package us.kanddys.laia.modules.ecommerce.services;

import org.springframework.web.multipart.MultipartFile;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoicePaymentDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la
 * clase InvoiceServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.3
 */
public interface InvoiceService {

   /**
    * Este método tiene la obligación de actualizar el estado de la orden.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param id
    * @param status
    * @return
    */
   public Integer updateInvoiceStatus(Long id, String status);

   /**
    * Este método tiene la obligación de actualizar el estado de la orden.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param status
    * @return Integer
    */
   public Integer updateInvoiceNote(Long invoiceId, String status);

   /**
    * Este método tiene la obligación de buscar una orden por su numero
    * de id.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param orderId
    * @return OrderDTO
    */
   public InvoiceDTO getInvoiceById(Long orderId);

   /**
    * Este método tiene la obligación de actualizar la imagen del voucher de la
    * orden.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param voucher
    * @param invoiceId
    * @return invoicePaymentDTO
    */
   public InvoicePaymentDTO updateInvoiceVoucher(MultipartFile voucher, Long invoiceId);
}
