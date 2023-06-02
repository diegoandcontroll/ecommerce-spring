package br.com.diegoandcontroll.ecommerce.dtos.cart;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequest {
  
  private UUID id;

  private UUID productId;

  private UUID userId;

  private Integer quantity;
}
