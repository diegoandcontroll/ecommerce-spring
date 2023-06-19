package br.com.diegoandcontroll.ecommerce.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegoandcontroll.ecommerce.domain.Category;
import br.com.diegoandcontroll.ecommerce.dtos.category.RequestCategory;
import br.com.diegoandcontroll.ecommerce.dtos.category.ResponseCategory;
import br.com.diegoandcontroll.ecommerce.services.CategoryService;
import br.com.diegoandcontroll.ecommerce.utils.CustomerUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService service;
  private final CustomerUtils utils;

  @GetMapping()
  public ResponseEntity<Page<Category>> findAllPaginable(Pageable pageable) {
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @GetMapping(path = "all")
  public ResponseEntity<List<ResponseCategory>> findAll() {
    List<Category> list = service.findAllCategoriesNoPaginable();
    return ResponseEntity.ok(list.stream().map(c -> new ResponseCategory(c)).collect(Collectors.toList()));
  }

  @PostMapping
  public ResponseEntity<ResponseCategory> create(@RequestBody RequestCategory category) {
    utils.verifyRole(utils.getRoleUserLogged(), "/api/v1/category");
    
    return new ResponseEntity<ResponseCategory>(service.createRequestCategory(category), HttpStatus.CREATED);
  }
  @PutMapping
  public ResponseEntity<ResponseCategory> update(@RequestBody ResponseCategory category){
    utils.verifyRole(utils.getRoleUserLogged(), "/api/v1/category");
    return ResponseEntity.ok(service.update(category));
  }
  @DeleteMapping("/{categoryId}")
  public ResponseEntity<String> delete(@PathVariable UUID categoryId) {
    utils.verifyRole(utils.getRoleUserLogged(), "/api/v1/category");
    return ResponseEntity.ok(service.deleteCategory(categoryId));
  }
}
