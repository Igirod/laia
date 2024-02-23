package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.AddressDTO;
import us.kanddys.laia.modules.ecommerce.services.AddressService;

@Controller
public class AddressController {

   @Autowired
   private AddressService addressService;

   @QueryMapping("gAddressUId")
   public List<AddressDTO> getAddressByUserId(@Argument Long userId, @Argument Integer page) {
      return addressService.findAddressByUserId(page, userId);
   }
}
