package us.kanddys.laia.modules.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import us.kanddys.laia.modules.ecommerce.controller.dto.BatchDTO;
import us.kanddys.laia.modules.ecommerce.services.BatchService;

@Controller
public class BatchController {
   
   @Autowired
   private BatchService batchService;

   @QueryMapping("gBatches")
   public List<BatchDTO> getBatchesByCalendarId(@Argument Long calendarId, @Argument String day) {
      return batchService.getBatchesByCalendarId(calendarId, day);
   }
}
