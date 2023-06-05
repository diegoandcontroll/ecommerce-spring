package br.com.diegoandcontroll.ecommerce.domain;


import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  
  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
  
  private int quantity;

  private Date createdAt;
}
