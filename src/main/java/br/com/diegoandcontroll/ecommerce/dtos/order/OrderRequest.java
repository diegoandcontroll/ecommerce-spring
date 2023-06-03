package br.com.diegoandcontroll.ecommerce.dtos.order;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
  private String productName;
  private int quantity;
  private double price;
  private UUID productId;
  private UUID userId;
}
