package br.com.diegoandcontroll.ecommerce.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.diegoandcontroll.ecommerce.domain.Product;
import br.com.diegoandcontroll.ecommerce.dtos.product.ProductRequest;
import br.com.diegoandcontroll.ecommerce.dtos.product.ProductResponse;
import br.com.diegoandcontroll.ecommerce.services.ImageService;
import br.com.diegoandcontroll.ecommerce.services.ProductService;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService service;
  private final ImageService imageService;

  @GetMapping()
  public ResponseEntity<Page<Product>> findAllPaginable(Pageable pageable) {
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @GetMapping(path = "all")
  public ResponseEntity<List<ProductResponse>> findAll() {
    return ResponseEntity.ok(service.findAllCategoriesNoPaginable());
  }

  @PostMapping
  public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest product) {
    ProductResponse createProductRequest = service.createProduct(product);
    return new ResponseEntity<ProductResponse>(createProductRequest, HttpStatus.CREATED);
  }

  @PostMapping("/upload/{productId}")
  public ResponseEntity<ProductResponse> uploadImage(@RequestPart("file") MultipartFile file, @PathVariable UUID productId) throws java.io.IOException {
    try {
      String imageUrl = imageService.uploadImage(file);
      return ResponseEntity.ok(service.saveImage(productId, imageUrl));
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

}
