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

  private String name;

  public ResponseCategory(Category c){
    id = c.getId();
    name = c.getName();
  }
}
