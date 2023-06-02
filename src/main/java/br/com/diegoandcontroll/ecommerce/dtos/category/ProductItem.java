package br.com.diegoandcontroll.ecommerce.dtos.category;

import java.util.UUID;

import br.com.diegoandcontroll.ecommerce.domain.Cart;
import br.com.diegoandcontroll.ecommerce.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItem {

  private UUID cartId;

  private Integer quantity;

  private Product product;

  public ProductItem(Cart cart){
    cartId = cart.getId();
    quantity = cart.getQuantity();
    product  = cart.getProduct();
  }

}
