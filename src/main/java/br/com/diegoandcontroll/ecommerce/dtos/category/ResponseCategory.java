package br.com.diegoandcontroll.ecommerce.dtos.category;

import java.util.UUID;

import br.com.diegoandcontroll.ecommerce.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCategory {
  private UUID id;
  private String description;
  
  private String categoryName;

  private String imageUrl;

  public ResponseCategory(Category c){
    id = c.getId();
    description = c.getDescription();
    categoryName = c.getCategoryName();
    imageUrl = c.getImageUrl();
  }
}
