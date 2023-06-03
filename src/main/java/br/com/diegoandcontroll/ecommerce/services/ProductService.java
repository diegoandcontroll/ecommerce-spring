package br.com.diegoandcontroll.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.diegoandcontroll.ecommerce.domain.Category;
import br.com.diegoandcontroll.ecommerce.domain.Product;
import br.com.diegoandcontroll.ecommerce.dtos.product.ProductRequest;
import br.com.diegoandcontroll.ecommerce.dtos.product.ProductResponse;
import br.com.diegoandcontroll.ecommerce.exceptions.CustomException;
import br.com.diegoandcontroll.ecommerce.repositories.CategoryRepo;
import br.com.diegoandcontroll.ecommerce.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepo repo;
  private final CategoryRepo categoryRepo;

  public Product findProductById(UUID productId){
    Optional<Product> productExists = repo.findById(productId);

    if(!productExists.isPresent()){
      new UsernameNotFoundException("Product not found");
    }

    return productExists.get();
  }
  public List<ProductResponse> findAllCategoriesNoPaginable() {
    List<Product> findAll = repo.findAll();
    List<ProductResponse> list = findAll.stream().map(p -> new ProductResponse(p)).toList();
    return list;
  }

  public Page<Product> findAll(Pageable pageable) {
    return repo.findAll(pageable);
  }


  public ProductResponse createProduct(ProductRequest p) {
    var objProduct = new Product();
    Optional<Category> findById = categoryRepo.findById(p.getCategoryId());
    if(!findById.isPresent()){
      new UsernameNotFoundException("Category not found");
    }
    objProduct.setName(p.getName());
    objProduct.setPrice(p.getPrice());
    objProduct.setImageUrl(p.getImageUrl());
    objProduct.setDescription(p.getDescription());
    objProduct.setCategory(findById.get());
    repo.save(objProduct);
    return new ProductResponse(objProduct);

  }

  public ProductResponse saveImage(UUID productId, String imageUrl){
    Product product = repo.findById(productId).orElseThrow(() -> new CustomException("NOT FOUND PRODUCT", HttpStatus.NOT_FOUND, "/api/v1/products"));

    product.setImageUrl(imageUrl);
    repo.save(product);
    return new ProductResponse(product);
  }
}
