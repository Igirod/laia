package us.kanddys.laia.modules.ecommerce.model;

import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase representa un producto.
 * 
 * @author Igirod0
 * @version 1.0.2
 */
@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "title")
   private String title;
   @Column(name = "price")
   private Double price;
   @Column(name = "stock")
   private Integer stock;
   @Column(name = "front_page")
   private String frontPage;
   @Column(name = "merchant_id")
   private Long merchantId;
   @Column(name = "status")
   private Integer status;
   @Column(name = "created_at")
   private Date createdAt;
   @Column(name = "type_of_sale")
   private String typeOfSale;
   @OneToMany(mappedBy = "product")
   private List<InvoiceProduct> invoiceProducts;

   public Product() {
   }

   public Product(Object object, Object object2, Double double1, Integer integer, Object object3, Object object4,
         Long id2, Integer integer2, Date currentDate, Object object5, Object object6) {
      //TODO Auto-generated constructor stub
   }
}
