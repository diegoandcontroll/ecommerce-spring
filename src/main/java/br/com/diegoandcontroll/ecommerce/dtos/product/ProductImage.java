package br.com.diegoandcontroll.ecommerce.dtos.product;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
  private UUID userId;

  private UUID productId;
}
