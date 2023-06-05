package br.com.diegoandcontroll.ecommerce.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthResponseCreated;
import br.com.diegoandcontroll.ecommerce.exceptions.CustomException;
import br.com.diegoandcontroll.ecommerce.services.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {
  private final AuthenticationService service;

  @GetMapping
  public ResponseEntity<Page<AuthResponseCreated>> findAllPaginable(Pageable pageable) {
    var role = getRole();
    if (role != "ADMIN") {
      throw new CustomException("Forbidden: You don't have permission to access this resource.", HttpStatus.FORBIDDEN,
          "/api/v1/customer");
    }
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @GetMapping(path = "me")
  public ResponseEntity<AuthResponseCreated> findUserLogged() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    var email = securityContext.getAuthentication().getName();

    return ResponseEntity.ok(service.findByUsername(email));

  }

  private String getRole() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      return userDetails.getAuthorities().iterator().next().getAuthority();
    }

    return null;
  }
}
