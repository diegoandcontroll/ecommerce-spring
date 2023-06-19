package br.com.diegoandcontroll.ecommerce.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.diegoandcontroll.ecommerce.domain.Product;
import br.com.diegoandcontroll.ecommerce.dtos.product.ProductRequest;
import br.com.diegoandcontroll.ecommerce.dtos.product.ProductResponse;
import br.com.diegoandcontroll.ecommerce.dtos.product.ProductUpdateRequest;
import br.com.diegoandcontroll.ecommerce.services.ImageService;
import br.com.diegoandcontroll.ecommerce.services.ProductService;
import br.com.diegoandcontroll.ecommerce.utils.CustomerUtils;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService service;
  private final ImageService imageService;
  private final CustomerUtils utils;
  @GetMapping()
  public ResponseEntity<Page<Product>> findAllPaginable(Pageable pageable) {
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @GetMapping("/category/{categoryId}")
  public ResponseEntity<List<ProductResponse>> findByCategory(@PathVariable UUID categoryId){
    return ResponseEntity.ok(service.findByCategory(categoryId));
  }

  @GetMapping(path = "all")
  public ResponseEntity<List<ProductResponse>> findAll() {
    return ResponseEntity.ok(service.findAllCategoriesNoPaginable());
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ProductResponse> create(@ModelAttribute ProductRequest request) throws java.io.IOException {
    utils.verifyRole(utils.getRoleUserLogged(), "/api/v1/product");
    try {
      
      String imageUrl = imageService.uploadImage(request.getImage());
      ProductResponse createProductRequest = service.createProduct(request.getName(),request.getDescription(),request.getPrice(), request.getCategoryId(),imageUrl);
      return new ResponseEntity<ProductResponse>(createProductRequest, HttpStatus.CREATED);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
  }
  @PutMapping
  public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductUpdateRequest request){
    utils.verifyRole(utils.getRoleUserLogged(), "/api/v1/product");
    return new ResponseEntity<ProductResponse>(service.update(request), HttpStatus.CREATED);
  }
  
  @PostMapping("/upload/{productId}")
  public ResponseEntity<ProductResponse> uploadImage(@RequestPart("file") MultipartFile file, @PathVariable UUID productId) throws java.io.IOException {
    utils.verifyRole(utils.getRoleUserLogged(), "/api/v1/product");
    try {
      String imageUrl = imageService.uploadImage(file);
      return ResponseEntity.ok(service.saveImage(productId, imageUrl));
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }
  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> findProduct(@PathVariable UUID productId){
    Product productEntity = service.findProductById(productId);
    return ResponseEntity.ok(new ProductResponse(productEntity));
  }
  @DeleteMapping("/{productId}")
  public ResponseEntity<String> delete(@PathVariable UUID productId){
    utils.verifyRole(utils.getRoleUserLogged(), "/api/v1/product");
    return ResponseEntity.ok(service.deleteProduct(productId));
  }

}
