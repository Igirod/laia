package us.kanddys.laia.modules.ecommerce.model.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase contiene los días del calendario.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class CalendarDay {

   private static final Integer MONDAY = 1;
   private static final Integer TUESDAY = 2;
   private static final Integer WEDNESDAY = 3;
   private static final Integer THURSDAY = 4;
   private static final Integer FRIDAY = 5;
   private static final Integer SATURDAY = 6;
   private static final Integer SUNDAY = 7;

   /**
    * Este método estático establece los rangos de busqueda.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param startDay
    * @return List<String>
    */
   public static List<String> getDays(List<Integer> combinations) {
      List<String> days = new ArrayList<>();
      for (Integer combination : combinations) {
         String combinationStr = Integer.toString(combination);
         for (char ch : combinationStr.toCharArray()) {
            Integer dayNumber = Character.getNumericValue(ch);
            String day = null;
            switch (dayNumber) {
               case 1:
                  day = "MON";
                  break;
               case 2:
                  day = "TUE";
                  break;
               case 3:
                  day = "WED";
                  break;
               case 4:
                  day = "THU";
                  break;
               case 5:
                  day = "FRI";
                  break;
               case 6:
                  day = "SAT";
                  break;
               case 7:
                  day = "SUN";
                  break;
               default:
                  System.out.println("Número de día inválido: " + dayNumber);
            }
            if (day != null && !days.contains(day)) {
               days.add(day);
            }
         }
      }
      return days;
   }
}
