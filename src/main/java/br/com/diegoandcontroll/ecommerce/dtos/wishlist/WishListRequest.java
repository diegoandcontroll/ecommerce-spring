package br.com.diegoandcontroll.ecommerce.dtos.wishlist;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishListRequest {
  private UUID productId;
}
