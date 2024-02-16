package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.PaymentDTO;
import us.kanddys.laia.modules.ecommerce.repository.PaymentCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

   @Autowired
   private PaymentCriteriaRepository paymentCriteriaRepository;

   @Override
   public List<PaymentDTO> findPaymentsPaginated(Integer page) {
      return paymentCriteriaRepository.findPaymentsPaginated(page).stream().map(PaymentDTO::new)
            .collect(Collectors.toList());
   }
}
