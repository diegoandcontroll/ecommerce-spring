package br.com.diegoandcontroll.ecommerce.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegoandcontroll.ecommerce.dtos.wishlist.WishListRequest;
import br.com.diegoandcontroll.ecommerce.dtos.wishlist.WishListResponse;
import br.com.diegoandcontroll.ecommerce.services.WishListService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishListController {
  private final WishListService service;

  @GetMapping()
  public ResponseEntity<Page<WishListResponse>> findAllPaginable(Pageable pageable){
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @GetMapping(path = "all")
  public ResponseEntity<List<WishListResponse>> findAll(){
    return ResponseEntity.ok(service.findAllCategoriesNoPaginable());
  }
  @GetMapping("/{userId}")
  public ResponseEntity<List<WishListResponse>> findAllByUserId(@PathVariable UUID userId){
    return ResponseEntity.ok(service.findAllByUserId(userId));
  }
  @PostMapping
  public ResponseEntity<WishListResponse> create(@RequestBody WishListRequest data){
    return new ResponseEntity<>(service.create(data), HttpStatus.CREATED);
  }

}
