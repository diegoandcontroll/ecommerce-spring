package br.com.diegoandcontroll.ecommerce.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegoandcontroll.ecommerce.domain.Customer;
import br.com.diegoandcontroll.ecommerce.dtos.cart.CartRequest;
import br.com.diegoandcontroll.ecommerce.dtos.cart.CartResponse;
import br.com.diegoandcontroll.ecommerce.dtos.cart.CartResponseAll;
import br.com.diegoandcontroll.ecommerce.services.CartService;
import br.com.diegoandcontroll.ecommerce.utils.CustomerUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
  private final CartService service;
  private final CustomerUtils utils;

  @GetMapping()
  public ResponseEntity<Page<CartResponse>> findAllPaginable(Pageable pageable) {
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @GetMapping(path = "all")
  public ResponseEntity<List<CartResponseAll>> findAll() {
    return ResponseEntity.ok(service.findAllCartsNoPaginable());
  }

  @GetMapping("/{userId}")
  public ResponseEntity<List<CartResponse>> findAllByUserId(@PathVariable UUID userId) {
    return ResponseEntity.ok(service.findAllByUserId(userId));
  }

  @PostMapping
  public ResponseEntity<CartResponse> create(@RequestBody CartRequest cartRequest) {
    Customer customer = utils.getCutomerLogger("/api/v1/cart");
    CartResponse createCartRequest = service.createCart(cartRequest, customer);
    return new ResponseEntity<CartResponse>(createCartRequest, HttpStatus.CREATED);
  }

  @DeleteMapping("/{cartId}")
  public ResponseEntity<String> delete(@PathVariable UUID cartId) {
    Customer customer = utils.getCutomerLogger("/api/v1/cart");
    return ResponseEntity.ok(service.deleteCart(cartId,customer));
  }

}
