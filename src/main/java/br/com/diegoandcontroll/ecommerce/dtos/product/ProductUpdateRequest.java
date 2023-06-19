package br.com.diegoandcontroll.ecommerce.dtos.product;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {
  private UUID id;
  
  private String description;
  
  private String name;

  private Double price;

  private UUID categoryId;
}

