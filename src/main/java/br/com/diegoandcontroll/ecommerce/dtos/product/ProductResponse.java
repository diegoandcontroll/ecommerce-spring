package br.com.diegoandcontroll.ecommerce.dtos.product;

import java.util.UUID;

import br.com.diegoandcontroll.ecommerce.domain.Category;
import br.com.diegoandcontroll.ecommerce.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
  private UUID id;

  private String description;
  
  private String name;

  private Double price;

  private String imageUrl;

  private Category category;

  public ProductResponse(Product c){
    id = c.getId();
    description = c.getDescription();
    name = c.getName();
    price = c.getPrice();
    imageUrl = c.getImageUrl();
    category = c.getCategory();
  }
}
