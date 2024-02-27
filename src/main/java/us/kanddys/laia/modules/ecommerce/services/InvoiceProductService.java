package us.kanddys.laia.modules.ecommerce.services;

import java.util.List;
import java.util.Optional;

import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceProductDTO;
import us.kanddys.laia.modules.ecommerce.controller.dto.InvoiceProductInputDTO;

/**
 * Esta interface contiene las obligaciones que debe implementar la clase
 * ShoppingCartServiceImpl.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
public interface InvoiceProductService {

   /**
    * Este metodo se encarga de agregar un producto a una factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param productId
    * @return Integer
    */
   public Integer addInvoiceProduct(Long invoiceId, Long productId);

   /**
    * Este metodo se encarga de actualizar la cantidad de un producto en una factura.
    *
    * @author Igirod0
    * @version 1.0.1
    * @param invoiceId
    * @param listInvoiceProducts
    * @return Integer
    */
   public Integer updateInvoiceProduct(Long invoiceId, List<InvoiceProductInputDTO> listInvoiceProducts);

   /**
    * Este método tiene la obligación de obtener todos los productos asociados
    * a una factura.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param invoiceId
    * @param page
    * @param limit
    * @return List<InvoiceProductDTO>
    */
   public List<InvoiceProductDTO> findInvoiceProductsByInvoiceId(Long invoiceId, Integer page, Optional<Integer> limit);
}
