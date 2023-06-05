package br.com.diegoandcontroll.ecommerce.dtos.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.diegoandcontroll.ecommerce.domain.Cart;
import br.com.diegoandcontroll.ecommerce.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponseAll {

  private UUID id;

  private UUID userId;

  private List<Product> products;

  private Integer quantity;

  public CartResponseAll(Cart cart) {
    List<Product> productList = new ArrayList<>();
    productList.add(cart.getProduct());
    id = cart.getId();
    userId = cart.getCustomer().getId();
    products = productList;
    quantity = cart.getQuantity();

  }
}
