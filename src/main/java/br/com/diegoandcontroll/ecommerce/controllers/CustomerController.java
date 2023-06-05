package br.com.diegoandcontroll.ecommerce.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthResponseCreated;
import br.com.diegoandcontroll.ecommerce.services.AuthenticationService;
import br.com.diegoandcontroll.ecommerce.utils.CustomerUtils;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {
  private final AuthenticationService service;
  private final CustomerUtils utils;

  @GetMapping
  public ResponseEntity<Page<AuthResponseCreated>> findAllPaginable(Pageable pageable) {
    var role = utils.getRoleUserLogged();
    utils.verifyRole(role, "/api/v1/customer");
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @GetMapping(path = "me")
  public ResponseEntity<AuthResponseCreated> findUserLogged() {
    return ResponseEntity.ok(service.findByUsername(utils.getUsernameCustomerLogged()));
  }

}
