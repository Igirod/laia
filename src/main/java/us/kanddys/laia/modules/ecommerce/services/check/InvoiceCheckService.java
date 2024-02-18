package us.kanddys.laia.modules.ecommerce.services.check;

/**
 * Esta interface contiene las obligaciones que debe implementar la
 * clase InvoiceCheckServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface InvoiceCheckService {
   
   /**
    * Este método verifica si el id del comerciante existe.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param merchantId
    * @return true si existe y false si no.
    */
   public Boolean existsMerchantId(Long merchantId);
   
   /**
    * Este método verifica si el id de la factura existe.
    *  
    * @author Igirod0
    * @version 1.0.0
    * @param userId
    * @return true si existe y false si no.
    */
   public Boolean existsUserEmail(String userEmail);
   
   /** 
    * Este método verifica si el id del carro de compras existe.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param shoppingCartId
    * @return true si existe y false si no.
    */
   public Boolean existsShoppingCartId(Long shoppingCartId);

   /**
    * Este método verifica los datos de la factura para ver si son validos.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param merchantId
    * @param shoppingCartId
    * @param userEmail
    */
   public Boolean checkInvoiceData(Long merchantId, Long shoppingCartId, String userEmail);

   /**
    * Este método verifica si el id de pago existe.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param paymentId
    * @return true si existe y false si no.
    */
   public Boolean existsPaymentId(Long paymentId);
}
