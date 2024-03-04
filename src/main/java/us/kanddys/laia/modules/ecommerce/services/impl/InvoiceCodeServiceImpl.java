package us.kanddys.laia.modules.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.kanddys.laia.modules.ecommerce.model.Utils.DateUtils;
import us.kanddys.laia.modules.ecommerce.repository.OrderJpaRepository;
import us.kanddys.laia.modules.ecommerce.services.InvoiceCodeService;

/**
 * Esta clase implementa las obligaciones de la interface
 * InvoiceCodeService.
 * 
 * @author Igirod0
 * @version 1.0.1
 */
@Service
public class InvoiceCodeServiceImpl implements InvoiceCodeService {

   @Autowired
   private OrderJpaRepository orderJpaRepository;

   @Override
   public String generateInvoiceCode(Long merchantId, Long orderId) {
      var maxorderId = (orderJpaRepository.findMaxOrderIdByMerchantIdAndStatus(merchantId));
      var dateSplitted = DateUtils.getCurrentDateStringWitheoutTime().split("-");
      var code = dateSplitted[0] + dateSplitted[1] + dateSplitted[2] + "MV" + merchantId + "N"
            + (maxorderId == null ? "1" : (maxorderId + 1L)).toString();
      orderJpaRepository.updateCodeByOrderId(code, orderId);
      return code;
   }

}
