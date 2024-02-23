package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceInputDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;

public interface InvoiceService {

   /**
    * Este método se encarga de buscar las facturas de un comerciante por su mail y
    * otros
    * parámetros opcionales.
    *
    * @author Igirod
    * @version 1.0.1
    * @param page
    * @param merchantId
    * @param userEmail
    * @param status
    * @return InvoiceDTO
    */
   public List<InvoiceDTO> findInvoicesByMerchantIdAndOptionalParamsPaginated(Integer page, Long merchantId,
         Optional<String> userEmail,
         Optional<InvoiceStatus> status);

   /**
    * Este método se encarga de crear una factura.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param userId
    * @param merchantId
    * @return InvoiceDTO
    */
   public InvoiceDTO createInvoice(Long userId, Long merchantId);

   /**
    * Este método se encarga de actualizar una factura.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param invoiceInputDTO
    * @return InvoiceDTO
    */
   public InvoiceDTO updateInvoice(InvoiceInputDTO invoiceInputDTO);

   /**
    * Este método se encarga de buscar una factura por su userId, merchantId y
    * status.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param userId
    * @param merchantId
    * @param status
    * @return InvoiceDTO
    */
   public InvoiceDTO findInvoiceByUserIdAndMerchantIdAndStatus(Long userId, Long merchantId, InvoiceStatus status);

   /**
    * Este método se encarga de actualizar el mensaje de una factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param message
    * @return 
    */
   public Integer updateInvoiceMessage(Long invoiceId, Integer message);

   /**
    * Este método se encarga de actualizar el pago de una factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param payment
    * @return Integer
    */
   public Integer updateInvoicePayment(Long invoiceId, Long paymentId);

   /**
    * Este método se encarga de actualizar la nota de una factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param note
    * @return Integer
    */
   public Integer updateInvoiceNote(Long invoiceId, String note);

   /**
    * Este método se encarga de actualizar el estado de una factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param status
    * @return Integer
    */
   public Integer updateInvoiceStatus(Long invoiceId, InvoiceStatus status);

   /**
    * Este método se encarga de actualizar el comprobante de una factura.
    *
    *
    * @author Igirod0
    * @version 1.0.0
    * @param voucher
    * @param invoiceId
    * @return String
    */
   public Integer updateInvoiceVoucher(MultipartFile voucher, Long invoiceId);
}
