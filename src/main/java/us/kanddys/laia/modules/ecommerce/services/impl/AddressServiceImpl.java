package us.kanddys.laia.modules.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.kanddys.laia.modules.ecommerce.controller.dto.AddressDTO;
import us.kanddys.laia.modules.ecommerce.repository.AddressCriteriaRepository;
import us.kanddys.laia.modules.ecommerce.services.AddressService;

/**
 * Esta clase implementa las obligaciones de la interface AddressService.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Service
public class AddressServiceImpl implements AddressService {

   @Autowired
   private AddressCriteriaRepository addressCriteriaRepository;

   @Override
   public List<AddressDTO> findAddressByUserId(Integer page, Long userId) {
      return addressCriteriaRepository.findAddressPaginated(page, userId).stream().map(AddressDTO::new)
            .collect(Collectors.toList());
   }

}
