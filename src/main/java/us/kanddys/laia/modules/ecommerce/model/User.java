package us.kanddys.laia.modules.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "name")
   private String name;
   @Column(name = "last_name")
   private String lastName;
   @Column(name = "email")
   private String mail;
   @Column(name = "password")
   private String password;
   @Column(name = "phone")
   private String phone;
   @Column(name = "image")
   private String image;
   @Column(name = "first")
   private Integer first;

   public User() {
   }

   public User(Boolean invoice) {
      super();
      this.password = "cocodrilo";
   }
}
