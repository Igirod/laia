package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceInputDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderPaymentDTO;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;

/**
 * Esta interface contiene las obligaciones que debe implementar la
 * clase InvoiceServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.3
 */
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
         Optional<Status> status);

   /**
    * Este método se encarga de crear una factura.
    *
    * @author Igirod0
    * @version 1.0.3
    * @param merchantId
    * @param userId
    * @return InvoiceDTO
    */
   public InvoiceDTO createInvoice(Long merchantId, Long userId);

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
    * @param invoiceId
    * @return InvoiceDTO
    */
   public InvoiceDTO findInvoiceById(Long invoiceId);

   /**
    * Este método se encarga de actualizar el mensaje de una factura.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param invoiceId
    * @param message
    * @return Integer
    */
   public Integer updateInvoiceMessage(Long invoiceId, String message);

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
   public Integer updateInvoiceStatus(Long invoiceId, Status status);

   /**
    * Este método se encarga de actualizar el comprobante de una factura.
    *
    *
    * @author Igirod0
    * @version 1.0.0
    * @param voucher
    * @param invoiceId
    * @param paymentId
    * @param date
    * @param batchId
    * @param merchantId
    * @param userId
    * @param addressLat
    * @param addressLng
    * @param addressDirection
    * @param reservationType
    * @return String
    */
   public OrderPaymentDTO updateOrderVoucher(MultipartFile voucher, Long invoiceId, Long paymentId,
         String date, Long batchId,
         Long merchantId,
         Long userId, String addressLat, String addressLng, String addressDirection, String reservationType);

   /**
    * Este método se encarga de actualizar la dirección de una factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param title
    * @param direction
    * @param lat
    * @param lng
    * @return Integer
    */
   public Integer updateInvoiceAddress(Long invoiceId, String direction, String lat, String lng, Integer addressNumber,
         String type);

   /**
    * Este método tiene la obligación de actualizar la fecha de reservación
    * asociada a una factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param date
    * @param batchId
    * @return Integer
    */
   public Integer updateInvoiceReservation(Long invoiceId, String date, Long batchId);
}
