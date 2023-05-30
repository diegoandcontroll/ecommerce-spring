package br.com.diegoandcontroll.ecommerce.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegoandcontroll.ecommerce.domain.Product;
import br.com.diegoandcontroll.ecommerce.dtos.product.ProductRequest;
import br.com.diegoandcontroll.ecommerce.dtos.product.ProductResponse;
import br.com.diegoandcontroll.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService service;

  @GetMapping()
  public ResponseEntity<Page<Product>> findAllPaginable(Pageable pageable){
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @GetMapping(path = "all")
  public ResponseEntity<List<ProductResponse>> findAll(){
    return ResponseEntity.ok(service.findAllCategoriesNoPaginable());
  }

  @PostMapping
  public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest product) {
    ProductResponse createProductRequest = service.createProduct(product);
    return new ResponseEntity<ProductResponse>(createProductRequest, HttpStatus.CREATED);
  }

  
}
