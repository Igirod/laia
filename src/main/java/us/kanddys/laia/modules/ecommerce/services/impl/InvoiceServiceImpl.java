package us.kanddys.laia.modules.ecommerce.services.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceInputDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.MailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.OrderPaymentDTO;
import us.kanddys.laia.modules.ecommerce.exception.IOJavaException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceCheckCodeException;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.InvoiceProductId;
import us.kanddys.laia.modules.ecommerce.model.Order;
import us.kanddys.laia.modules.ecommerce.model.OrderProduct;
import us.kanddys.laia.modules.ecommerce.model.Reservation;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.Status;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.MerchantJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.OrderProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ReservationJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.InvoiceCodeService;
import us.kanddys.laia.modules.ecommerce.services.InvoiceService;
import us.kanddys.laia.modules.ecommerce.services.MailSenderService;
import us.kanddys.laia.modules.ecommerce.services.check.InvoiceCheckService;
import us.kanddys.laia.modules.ecommerce.services.check.ProductCheckStockService;
import us.kanddys.laia.modules.ecommerce.services.storage.FirebaseStorageService;

/**
 * Esta clase implementa las obligaciones de la interface InvoiceService.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

   @Autowired
   private InvoiceCriteriaRepository invoiceCriteriaRepository;

   @Autowired
   private InvoiceJpaRepository invoiceJpaRepository;

   @Autowired
   private ProductCheckStockService productCheckStockService;

   @Autowired
   private InvoiceCheckService invoiceCheckService;

   @Autowired
   private FirebaseStorageService firebaseStorageService;

   @Autowired
   private InvoiceProductJpaRepository invoiceProductJpaRepository;

   @Autowired
   private InvoiceProductCriteriaRepository invoiceProductCriteriaRepository;

   @Autowired
   private ReservationJpaRepository reservationJpaRepository;

   @Autowired
   private InvoiceCodeService invoiceCodeService;

   @Autowired
   private OrderJpaRepository orderJpaRepository;

   @Autowired
   private OrderProductJpaRepository orderProductJpaRepository;

   @Autowired
   private MerchantJpaRepository merchantJpaRepository;

   @Autowired
   private MailSenderService mailSenderService;

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Override
   public List<InvoiceDTO> findInvoicesByMerchantIdAndOptionalParamsPaginated(Integer page, Long merchantId,
         Optional<String> userEmail, Optional<Status> status) {
      return invoiceCriteriaRepository.findInvoicesPaginated(page, merchantId, userEmail, status).stream().map(t -> {
         try {
            return new InvoiceDTO(t);
         } catch (IOException e) {
            throw new IOJavaException(e.getMessage());
         }
      }).collect(Collectors.toList());
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public InvoiceDTO createInvoice(Long merchantId, Long userId) {
      try {
         return new InvoiceDTO(invoiceJpaRepository.save(new Invoice(userId, merchantId)));
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public InvoiceDTO updateInvoice(InvoiceInputDTO invoiceInputDTO) {
      if (invoiceCheckService.existsMerchantId(invoiceInputDTO.getMerchantId())) {
         var updateInvoice = invoiceJpaRepository.findById(invoiceInputDTO.getId());
         if (updateInvoice.isPresent()) {
            try {
               updateInvoice = Optional.of(new Invoice(invoiceInputDTO.getId(), invoiceInputDTO,
                     invoiceProductJpaRepository.countByInvoiceId(invoiceInputDTO.getId())));
               return Optional.of(new InvoiceDTO(invoiceJpaRepository.save(updateInvoice.get()))).get();
            } catch (ParseException e) {
               throw new RuntimeException(e);
            } catch (IOException e) {
               throw new IOJavaException(e.getMessage());
            }
         } else {
            throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
         }
      }
      throw new InvoiceCheckCodeException(ExceptionMessage.INOICE_CHECK_SERVICE);
   }

   @Override
   public InvoiceDTO findInvoiceById(Long invoiceId) {
      var invoice = invoiceJpaRepository.findById(invoiceId);
      if (invoice.isEmpty())
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      try {
         Map<String, Object> map = merchantJpaRepository
               .findLatAndLngAndAddressByMerchantId(invoice.get().getMerchantId());
         invoice.get().setCount(
               invoiceProductJpaRepository.countByInvoiceId(invoice.get() == null ? 0 : invoice.get().getId()));
         var selectedInvoiceDTO = new InvoiceDTO(invoice.get());
         selectedInvoiceDTO.setMerchantDirection((String) map.get("address"));
         selectedInvoiceDTO.setMerchantLat((String) map.get("lat"));
         selectedInvoiceDTO.setMerchantLng((String) map.get("lng"));
         return selectedInvoiceDTO;
      } catch (IOException e) {
         throw new IOJavaException(e.getMessage());
      }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceMessage(Long invoiceId, String message) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      invoiceJpaRepository.updateMessageByInvoiceId(message, invoiceId);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceNote(Long invoiceId, String note) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      invoiceJpaRepository.updateNoteByInvoiceId(note, invoiceId);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceStatus(Long invoiceId, Status status) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      invoiceJpaRepository.updateStatusByInvoiceId(status.toString(), invoiceId);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public OrderPaymentDTO updateOrderVoucher(MultipartFile voucher, Long invoiceId, Long paymentId,
         String date, Long batchId,
         Long merchantId,
         Long userId, String addressLat, String addressLng, String addressDirection) {
      var invoice = invoiceJpaRepository.findById(invoiceId);
      if (invoice.isEmpty())
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      // * Creación de la orden definitiva.
      Order order = new Order(invoice.get());
      order.setCode(invoiceCodeService.generateInvoiceCode(merchantId, invoiceId));
      order.setVoucher(firebaseStorageService.uploadFile(voucher, "vouchers"));
      order.setAddressDirection(addressDirection);
      order.setAddressLat(addressLat);
      order.setAddressLng(addressLng);
      updateOrderPayment(invoiceId, paymentId, date, batchId, merchantId, userId, order);
      return new OrderPaymentDTO(order.getVoucher(), order.getCode(), order.getId());
   }

   private void updateOrderPayment(Long invoiceId, Long paymentId, String date, Long batchId, Long merchantId,
         Long userId, Order order) {
      order.setBatchId(batchId);
      order.setPaymentId(paymentId);
      order.setStatus(Status.PENDING);
      order.setReservation(date);
      order.setMerchantId(merchantId);
      try {
         reservationJpaRepository.save(
               new Reservation(null, merchantId, userId, batchId, DateUtils.convertStringToDateWithoutTime(date)));
         order.setCreateAt(DateUtils.getCurrentDate());
      } catch (ParseException e) {
         throw new RuntimeException("Error al convertir la fecha");
      }
      var invoiceProductsIds = new ArrayList<InvoiceProductId>();
      var newOrder = orderJpaRepository.save(order);
      List<OrderProduct> listOrderProducts = invoiceProductCriteriaRepository.findInvoiceProductsByInvoiceId(invoiceId)
            .stream().map(t -> {
               invoiceProductsIds.add(new InvoiceProductId(invoiceId, t.getProduct().getId()));
               productCheckStockService.checkStock(t.getProduct().getId(), t.getProduct().getStock(), t.getQuantity());
               return new OrderProduct(t, newOrder.getId());
            }).collect(Collectors.toList());
      // * Guardado de la orden y sus productos.
      orderProductJpaRepository.saveAll(listOrderProducts);
      // ! Eliminamos los datos asociados a la factura anterior.
      invoiceProductCriteriaRepository.deleteProductsByInvoiceId(invoiceId);
      invoiceJpaRepository.deleteById(invoiceId);
      try {
         mailSenderService.sendUserOrder(new MailDTO(userJpaRepository.findEmailByUserId(userId), "Order - Detail",
               "user", "", newOrder.getId()));
         mailSenderService.sendUserOrder(new MailDTO(merchantJpaRepository.findEmailByMerchantId(merchantId),
               "New sale - Detail", "merchant", "", newOrder.getId()));
      } catch (MessagingException e) {
         throw new RuntimeException("Error al enviar el correo");
      }
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceAddress(Long invoiceId, String direction, String lat, String lng, Integer addressNumber,
         String type) {
      if (invoiceJpaRepository.existsById(invoiceId) == false)
         throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      invoiceJpaRepository.updateAddressByInvoiceId(direction, lat, lng, invoiceId, addressNumber, type);
      return 1;
   }

   @Transactional(rollbackOn = { Exception.class, RuntimeException.class })
   @Override
   public Integer updateInvoiceReservation(Long invoiceId, String date, Long batchId) {
      if (invoiceJpaRepository.existsById(invoiceId)) {
         invoiceJpaRepository.updateReservationAndBatchIdByInvoiceId(date, batchId, invoiceId);
         return 1;
      } else
         return 0;
   }
}
