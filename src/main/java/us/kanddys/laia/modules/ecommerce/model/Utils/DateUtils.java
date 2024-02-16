package us.kanddys.laia.modules.ecommerce.model.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Esta clase representa un conjunto de utilidades para el manejo de fechas.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class DateUtils {

   /**
    * Convierte un String a Date con el formato yyyy-MM-dd HH:mm:ss.
    * 
    * @author Igirod0
    * @version 1.0.0
    * @param dateString
    * @return Date
    */
   public static Date convertStringToDate(String dateString) throws ParseException {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
   }

   /**
    * Este método tiene la obligacion de devolver el String de la fecha actual
    * con el formato yyyy-MM-dd.
    *
    * @author Igirod0
    * @version 1.0.0
    * @return String
    */
   public static String getCurrentDateString() {
      return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
   }
}
