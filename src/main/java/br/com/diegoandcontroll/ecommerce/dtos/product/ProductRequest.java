package br.com.diegoandcontroll.ecommerce.dtos.product;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
  private String description;
  
  private String name;

  private Double price;

  private MultipartFile image;

  private UUID categoryId;
}
