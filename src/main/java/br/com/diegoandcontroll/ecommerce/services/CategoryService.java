package br.com.diegoandcontroll.ecommerce.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.diegoandcontroll.ecommerce.domain.Category;
import br.com.diegoandcontroll.ecommerce.dtos.category.RequestCategory;
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
}
