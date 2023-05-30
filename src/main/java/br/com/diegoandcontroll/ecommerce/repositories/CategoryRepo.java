package br.com.diegoandcontroll.ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegoandcontroll.ecommerce.domain.Category;

public interface CategoryRepo extends JpaRepository<Category, UUID>{
  
}
