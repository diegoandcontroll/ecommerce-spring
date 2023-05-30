package br.com.diegoandcontroll.ecommerce.dtos.product;

import java.util.UUID;

import br.com.diegoandcontroll.ecommerce.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
  private String description;
  
  private String name;

  private Double price;

  private String imageUrl;

  private UUID categoryId;

  public ProductRequest(Product c){
    description = c.getDescription();
    name = c.getName();
    price = c.getPrice();
    imageUrl = c.getImageUrl();
    categoryId = c.getCategory().getId();
  }
}
