package br.com.diegoandcontroll.ecommerce.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.diegoandcontroll.ecommerce.domain.Category;
import br.com.diegoandcontroll.ecommerce.dtos.category.RequestCategory;
import br.com.diegoandcontroll.ecommerce.exceptions.CustomException;
import br.com.diegoandcontroll.ecommerce.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepo categoryRepo;
  
  public List<Category> findAllCategoriesNoPaginable() {
    return categoryRepo.findAll();
  }

  public Page<Category> findAll(Pageable pageable) {
    return categoryRepo.findAll(pageable);
  }

  public RequestCategory createRequestCategory(RequestCategory cat){
    var catObj = new Category();
    BeanUtils.copyProperties(cat, catObj);
    categoryRepo.save(catObj);
    return cat;
  }

  public String deleteCategory(UUID categoryId) {
    Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new CustomException("CATEGORY NOT FOUND", HttpStatus.NOT_FOUND, "/api/v1/category"));
    categoryRepo.delete(category);
    return "CATEGORY DELETED";
  }
}
