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

   /**
    * Este método analiza las combinaciones de los batches y registra
    * los días que trabaja el usuario.
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

   /**
    * Este método tiene la obligacion de devolver el dia correspondiente a la
    * cadena
    * de caracteres pasada por parametro.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param day
    * @return Integer
    */
   public static Integer getDayNumber(String day) {
      Integer dayNumber = null;
      switch (day) {
         case "MON":
            dayNumber = 1;
            break;
         case "TUE":
            dayNumber = 2;
            break;
         case "WED":
            dayNumber = 3;
            break;
         case "THU":
            dayNumber = 4;
            break;
         case "FRI":
            dayNumber = 5;
            break;
         case "SAT":
            dayNumber = 6;
            break;
         case "SUN":
            dayNumber = 7;
            break;
         default:
            System.out.println("Día inválido: " + day);
      }
      return dayNumber;
   }

   /**
    * Este método tiene la obligación de devolver el nombre del día de la semana
    * correspondiente al número pasado por parámetro.
    * 
    * @param day el número del día de la semana (1 para lunes, 2 para martes, etc.)
    * @return el nombre abreviado del día (por ejemplo, "MON" para lunes)
    */
   public static String getDayNumber(Integer day) {
      String dayName = null;
      switch (day) {
         case 1:
            dayName = "MON";
            break;
         case 2:
            dayName = "TUE";
            break;
         case 3:
            dayName = "WED";
            break;
         case 4:
            dayName = "THU";
            break;
         case 5:
            dayName = "FRI";
            break;
         case 6:
            dayName = "SAT";
            break;
         case 7:
            dayName = "SUN";
            break;
         default:
            System.out.println("Número de día inválido: " + day);
            break;
      }
      return dayName;
   }

}
