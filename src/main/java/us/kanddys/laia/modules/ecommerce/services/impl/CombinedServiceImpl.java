package us.kanddys.laia.modules.ecommerce.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedProductDetailDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.CombinedShopDTO;
import us.kanddys.laia.modules.ecommerce.exception.InvoiceNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.MerchantNotFoundException;
import us.kanddys.laia.modules.ecommerce.exception.utils.ExceptionMessage;
import us.kanddys.laia.modules.ecommerce.model.Invoice;
import us.kanddys.laia.modules.ecommerce.model.User;
import us.kanddys.laia.modules.ecommerce.model.Utils.CalendarDay;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.model.Utils.InvoiceStatus;
import us.kanddys.laia.modules.ecommerce.repository.BatchJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.CalendarJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.InvoiceProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.MerchantJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ProductJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.ReservationJpaRepository;
import us.kanddys.laia.modules.ecommerce.repository.UserJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.CombinedService;
import us.kanddys.laia.modules.ecommerce.services.ImageProductService;
import us.kanddys.laia.modules.ecommerce.services.ProductDetailService;
import us.kanddys.laia.modules.ecommerce.services.ProductService;

/**
 * Esta clase implementa las obligaciones de la interface CombinedService.
 * 
 * @author Igirod0
 * @version 1.0.2
 */
@Service
public class CombinedServiceImpl implements CombinedService {

   @Autowired
   private MerchantJpaRepository merchantJpaRepository;

   @Autowired
   private CalendarJpaRepository calendarJpaRepository;

   @Autowired
   private InvoiceJpaRepository invoiceJpaRepository;

   @Autowired
   private UserJpaRepository userJpaRepository;

   @Autowired
   private ProductJpaRepository productJpaRepository;

   @Autowired
   private ProductService productService;

   @Autowired
   private ImageProductService imageProductService;

   @Autowired
   private InvoiceProductJpaRepository invoiceProductJpaRepository;

   @Autowired
   private ProductDetailService productDetailService;

   @Autowired
   private BatchJpaRepository batchJpaRepository;

   @Autowired
   private ReservationJpaRepository reservationJpaRepository;

   @Override
   public CombinedShopDTO findCombinedShop(String slug, Optional<Long> userId) {
      Map<String, Object> merchant = merchantJpaRepository.findMerchantIdAndTitle(slug);
      if (merchant == null)
         throw new MerchantNotFoundException(ExceptionMessage.MERCHANT_NOT_FOUND);
      Long merchantId = merchant.get("id") == null ? null : Long.valueOf(merchant.get("id").toString());
      String merchantTitle = (merchant.get("title") == null ? null : merchant.get("title").toString());
      var products = productService.getProductsPaginated(1, merchantId, Optional.of(1));
      Invoice invoice = invoiceIfUserPresent(userId, merchantId);
      return new CombinedShopDTO(merchantId, merchantTitle, products, invoice.getId(),
            invoiceProductJpaRepository.countByInvoiceId(invoice.getId()), invoice.getUserId());
   }

   @Override
   public CombinedProductDTO findCombinedProduct(Long productId, String slug, Optional<Long> userId) {
      Map<String, Object> merchant = merchantJpaRepository.findMerchantIdAndTitle(slug);
      if (merchant == null)
         throw new MerchantNotFoundException(ExceptionMessage.MERCHANT_NOT_FOUND);
      Long merchantId = merchant.get("id") == null ? null : Long.valueOf(merchant.get("id").toString());
      String merchantTitle = (merchant.get("title") == null ? null : merchant.get("title").toString());
      var images = imageProductService.getImagesProductByProductId(productId);
      var details = productDetailService.getProductDetailsByProductId(productId);
      var product = productService.getProductById(productId);
      var invoice = invoiceIfUserPresent(userId, merchantId);
      return new CombinedProductDTO(merchantId, merchantTitle, product.getId(), product.getTitle(), product.getPrice(),
            product.getFrontPage(),
            images, details, product.getStock(), invoice.getId(),
            invoiceProductJpaRepository.countByInvoiceId(invoice.getId()),
            invoice.getUserId(),
            (userId.isPresent() && invoiceProductJpaRepository.countByInvoiceId(invoice.getId()) > 0)
                  ? (invoiceProductJpaRepository.existInvoiceProductByInvoiceIdAndProductId(invoice.getId(),
                        productId) != null ? 1
                              : 0)
                  : 0);
   }

   /**
    * Este método se encarga de buscar una factura por usuario y comercio, si no
    * existe crea una nueva factura.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param userId
    * @param merchantId
    * @return Invoice
    */
   private Invoice invoiceIfUserPresent(Optional<Long> userId, Long merchantId) {
      Invoice invoice;
      if (userId.isPresent()) {
         invoice = invoiceJpaRepository.findInvoiceIdByUserIdAndMerchantIdAndStatus(userId.get(), merchantId,
               InvoiceStatus.INITIAL.toString());
         if (invoice == null) {
            invoice = createNewInvoice(userId.get(), merchantId);
         }
         if (invoice == null)
            throw new InvoiceNotFoundException(ExceptionMessage.INVOICE_NOT_FOUND);
      } else {
         invoice = createNewInvoice(userJpaRepository.save(new User()).getId(), merchantId);
      }
      return invoice;
   }

   @Override
   public CombinedProductDetailDTO findCombinedProductDetail(Long productId, Optional<Long> invoiceId,
         Long merchantId) {
      CombinedProductDetailDTO combinedProductDetailDTO = new CombinedProductDetailDTO(
            productJpaRepository.findStockByProductId(productId), invoiceId.isPresent()
                  ? (invoiceProductJpaRepository.existInvoiceProductByInvoiceIdAndProductId(invoiceId.get(),
                        productId) != null ? 1
                              : 0)
                  : 0,
            null, null, imageProductService.getImagesProductByProductId(productId),
            productDetailService.getProductDetailsByProductId(productId));
      return findMerchantDirectionAndFirstShippingDate(combinedProductDetailDTO, merchantId);
   }

   private CombinedProductDetailDTO findMerchantDirectionAndFirstShippingDate(
         CombinedProductDetailDTO combinedProductDetailDTO,
         Long merchantId) {
      combinedProductDetailDTO.setMerchantDirection(merchantJpaRepository.findAddressByMerchantId(merchantId));
      List<String> workingDays = CalendarDay.getDays(batchJpaRepository
            .findDaysByCalendarId(calendarJpaRepository.findCalendarIdByMerchantId(merchantId).get()));
      String reservations = reservationJpaRepository.findLatestDateByMerchantId(merchantId);
      if (reservations == null) {
         String formatoString = "yyyy-MM-dd";
         SimpleDateFormat formato = new SimpleDateFormat(formatoString);
         Date fecha;
         try {
            fecha = formato.parse("2024-03-17");
         } catch (ParseException e) {
            throw new RuntimeException("Error al parsear la fecha");
         }
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(fecha);
         System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
      }

      // Calendar calendar = Calendar.getInstance();
      // try {
      // calendar.setTime(DateUtils.convertStringToDateWithoutTime("2024-03-20"));
      // } catch (ParseException e) {
      // // TODO Auto-generated catch block
      // e.printStackTrace();
      // }
      // int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
      // String[] daysOfWeek = { "", "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"
      // };
      // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      // System.out
      // .println(CalendarDay.getDayNumber(calendar.get(Calendar.DAY_OF_WEEK) + 1) + "
      // " + sdf.format("2024-03-20"));
      // return combinedProductDetailDTO;
   }

   /**
    * Este método se encarga de crear una nueva factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param userId
    * @param merchantId
    * @return Invoice
    */
   private Invoice createNewInvoice(Long userId, Long merchantId) {
      var newInvoice = new Invoice();
      newInvoice.setUserId(userId);
      newInvoice.setMerchantId(merchantId);
      newInvoice.setStatus(InvoiceStatus.INITIAL);
      newInvoice.setTotal(0.0);
      return invoiceJpaRepository.save(newInvoice);
   }
}
