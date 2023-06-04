package br.com.diegoandcontroll.ecommerce.dtos.wishlist;

import java.util.UUID;

import br.com.diegoandcontroll.ecommerce.domain.Product;
import br.com.diegoandcontroll.ecommerce.domain.WishList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishListResponse {
  private UUID id;

  private UUID customerId;

  private String customerName;

  private String customerEmail;

  private Product product;
  
  public WishListResponse(WishList w){
    id = w.getId();
    customerId = w.getCustomer().getId();
    customerName = w.getCustomer().getFirstname();
    customerEmail = w.getCustomer().getEmail();
    product = w.getProduct();
  }
}
