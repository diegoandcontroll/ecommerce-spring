package br.com.diegoandcontroll.ecommerce.dtos.wishlist;

import java.util.UUID;

import br.com.diegoandcontroll.ecommerce.domain.Product;
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

  private UUID userId;

  private String name;

  private String email;

  private Product product;
  
}
