package us.kanddys.laia.modules.ecommerce.services.storage.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.awt.Color;
import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

/**
 * Esta clase contiene metodos para el manejo del formato de imagenes.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
public class ImageFormatUtils {

   /**
    * Este método rescala la imagen a una resolución de 300x300.
    *
    * @author Igirod0
    * @version 1.0.0
    * @param image
    * @return byte[]
    */
   public static byte[] resizeImage(MultipartFile inputFile, Boolean aspectRatio11) {
      byte[] imageInByte = null;
      try {
         BufferedImage originalImage = ImageIO.read(inputFile.getInputStream());
         int originalWidth = originalImage.getWidth();
         int originalHeight = originalImage.getHeight();
         int newWidth, newHeight;

         // Si aspectRatio11 es verdadero, usamos una relación de aspecto de 1:1, de lo
         // contrario usamos 16:9
         if (aspectRatio11) {
            newWidth = newHeight = 300; // Para una relación de aspecto de 1:1, establecemos las dimensiones a 300x300
         } else {
            double aspectRatio = 16.0 / 9.0;
            double originalAspectRatio = (double) originalWidth / originalHeight;

            if (originalAspectRatio > aspectRatio) {
               newWidth = 382;
               newHeight = (int) (newWidth / aspectRatio);
            } else {
               newHeight = 215;
               newWidth = (int) (newHeight * aspectRatio);
            }
         }

         // Crear una nueva imagen con el nuevo ancho y alto
         BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
         Graphics2D g = resizedImage.createGraphics();

         // Rellenar la imagen con un color transparente
         g.setColor(new Color(0, 0, 0, 0));
         g.fillRect(0, 0, newWidth, newHeight);

         // Dibujar la imagen original en el centro de la nueva imagen
         g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
         g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
         g.dispose();

         // Convertir la imagen final a un array de bytes
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ImageIO.write(resizedImage, "png", baos);
         imageInByte = baos.toByteArray();
         baos.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return imageInByte;
   }
}
