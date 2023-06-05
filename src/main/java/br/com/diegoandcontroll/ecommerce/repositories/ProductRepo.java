package br.com.diegoandcontroll.ecommerce.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegoandcontroll.ecommerce.domain.Category;
import br.com.diegoandcontroll.ecommerce.domain.Product;

public interface ProductRepo extends JpaRepository<Product, UUID>{
  List<Product> findAllByCategory(Category category);
}
