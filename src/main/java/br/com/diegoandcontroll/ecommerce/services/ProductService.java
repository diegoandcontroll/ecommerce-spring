package br.com.diegoandcontroll.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.diegoandcontroll.ecommerce.domain.Category;
import br.com.diegoandcontroll.ecommerce.domain.Product;
import br.com.diegoandcontroll.ecommerce.dtos.product.ProductResponse;
import br.com.diegoandcontroll.ecommerce.dtos.product.ProductUpdateRequest;
import br.com.diegoandcontroll.ecommerce.exceptions.CustomException;
import br.com.diegoandcontroll.ecommerce.repositories.CategoryRepo;
import br.com.diegoandcontroll.ecommerce.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepo repo;
  private final CategoryRepo categoryRepo;

  public Product findProductById(UUID productId) {
    Optional<Product> productExists = repo.findById(productId);

    if (!productExists.isPresent()) {
      throw new CustomException("NOT FOUND PRODUCT", HttpStatus.NOT_FOUND, "/api/v1/products");
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

  public ProductResponse createProduct(String name, String description, Double price, UUID categoryId,
      String imageUrl) {
    var objProduct = new Product();
    Optional<Category> category = categoryRepo.findById(categoryId);
    if (!category.isPresent()) {
      throw new CustomException("NOT FOUND CATEGORY", HttpStatus.NOT_FOUND, "/api/v1/products");
    }
    objProduct.setName(name);
    objProduct.setPrice(price);
    objProduct.setImageUrl(imageUrl);
    objProduct.setDescription(description);
    objProduct.setCategory(category.get());
    repo.save(objProduct);
    return new ProductResponse(objProduct);

  }

  public ProductResponse saveImage(UUID productId, String imageUrl) {
    Product product = repo.findById(productId)
        .orElseThrow(() -> new CustomException("NOT FOUND PRODUCT", HttpStatus.NOT_FOUND, "/api/v1/products"));

    product.setImageUrl(imageUrl);
    repo.save(product);
    return new ProductResponse(product);
  }

  public List<ProductResponse> findByCategory(UUID categoryId) {
    Optional<Category> category = categoryRepo.findById(categoryId);
    if (!category.isPresent())
      throw new CustomException("NOT FOUND CATEGORY", HttpStatus.NOT_FOUND, "/api/v1/product");
    List<Product> products = repo.findAllByCategory(category.get());

    return products.stream().map(p -> new ProductResponse(p)).toList();
  }

  public String deleteProduct(UUID productId) {
    Product product = repo.findById(productId)
        .orElseThrow(() -> new CustomException("PRODUCT NOT FOUND", HttpStatus.NOT_FOUND, "/api/v1/product"));
    repo.delete(product);
    return "PRODUCT REMOVED";
  }

  public ProductResponse update(ProductUpdateRequest request) {
    Product product = findProductById(request.getId());
    if (request.getCategoryId() != null) {
      Category category = categoryRepo.findById(request.getCategoryId())
          .orElseThrow(() -> new CustomException("CATEGORY NOT FOUND", HttpStatus.NOT_FOUND, "/api/v1/product"));
      product.setCategory(category);
    }
    BeanUtils.copyProperties(request, product);
    repo.save(product);
    return new ProductResponse(product);
  }
}
