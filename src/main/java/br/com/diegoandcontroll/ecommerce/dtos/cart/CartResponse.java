package br.com.diegoandcontroll.ecommerce.dtos.cart;

import java.util.List;

import br.com.diegoandcontroll.ecommerce.dtos.category.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {

  private List<ProductItem> products;

  private Double total;
}
