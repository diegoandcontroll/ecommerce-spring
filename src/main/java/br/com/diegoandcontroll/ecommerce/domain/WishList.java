package br.com.diegoandcontroll.ecommerce.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wishlist")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishList {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @OneToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  private Date createdAt;
}
