package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;

import us.kanddys.laia.modules.ecommerce.controller.dto.PaymentDTO;

/**
 * Esta clase contiene las obligaciones que debe implementar la clase
 * PaymentServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public interface PaymentService {
   
   /**
    * Este método se encarga de buscar los pagos de forma paginada.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param page
    * @return List<PaymentDTO>
    */
   public List<PaymentDTO> findPaymentsPaginated(Integer page);
}
