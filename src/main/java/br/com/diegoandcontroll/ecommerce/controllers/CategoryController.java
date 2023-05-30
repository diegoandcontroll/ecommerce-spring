package br.com.diegoandcontroll.ecommerce.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegoandcontroll.ecommerce.domain.Category;
import br.com.diegoandcontroll.ecommerce.dtos.category.RequestCategory;
import br.com.diegoandcontroll.ecommerce.dtos.category.ResponseCategory;
import br.com.diegoandcontroll.ecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService service;

  @GetMapping()
  public ResponseEntity<Page<Category>> findAllPaginable(Pageable pageable){
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @GetMapping(path = "all")
  public ResponseEntity<List<ResponseCategory>> findAll(){
    List<Category> list = service.findAllCategoriesNoPaginable();
    return ResponseEntity.ok(list.stream().map(c -> new ResponseCategory(c)).collect(Collectors.toList()));
  }
  
  @PostMapping
  public ResponseEntity<RequestCategory> create(@RequestBody RequestCategory category) {
    RequestCategory createCategoryRequest = service.createRequestCategory(category);
    return new ResponseEntity<RequestCategory>(createCategoryRequest, HttpStatus.CREATED);
  }
}
