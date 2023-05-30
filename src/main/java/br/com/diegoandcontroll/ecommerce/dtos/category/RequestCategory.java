package br.com.diegoandcontroll.ecommerce.dtos.category;

import br.com.diegoandcontroll.ecommerce.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCategory {

  private String description;
  
  private String categoryName;

  private String imageUrl;

  public RequestCategory(Category c){
    description = c.getDescription();
    categoryName = c.getCategoryName();
    imageUrl = c.getImageUrl();
  }
}
