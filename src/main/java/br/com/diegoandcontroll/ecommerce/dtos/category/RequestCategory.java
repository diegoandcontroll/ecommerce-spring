package br.com.diegoandcontroll.ecommerce.dtos.category;

import br.com.diegoandcontroll.ecommerce.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCategory {
  
  private String name;


  public RequestCategory(Category c){
    name = c.getName();
  }
}
