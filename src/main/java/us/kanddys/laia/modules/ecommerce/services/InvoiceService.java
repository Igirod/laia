package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;

public interface InvoiceService {

   /**
    * Este método se encarga de buscar las facturas de un comerciante por su mail y
    * otros
    * parámetros opcionales.
    *
    * @author Igirod
    * @version 1.0.0
    * @param page
    * @param merchantEmail
    * @param userEmail
    * @param status
    * @return InvoiceDTO
    */
   public List<InvoiceDTO> findInvoicesByMerchantEmailPaginated(Integer page, String merchantEmail,
         Optional<String> userEmail,
         Optional<InvoiceStatus> status);

   /**
    * Este método se encarga de crear una factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceDTO
    * @return InvoiceDTO
    */
   public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);

   /**
    * Este método se encarga de actualizar una factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceDTO
    * @return InvoiceDTO
    */
   public InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO);
}
