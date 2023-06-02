package br.com.diegoandcontroll.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthRequest;
import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthRequestCreated;
import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthResponse;
import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthResponseCreated;
import br.com.diegoandcontroll.ecommerce.services.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService service;

  @PostMapping("/sign-up")
  public ResponseEntity<AuthResponseCreated> register(
      @RequestBody AuthRequestCreated request) {
    return new ResponseEntity<AuthResponseCreated>(service.register(request), HttpStatus.CREATED);
  }

  @PostMapping("/sign-in")
  public ResponseEntity<AuthResponse> authenticate(
      @RequestBody AuthRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @GetMapping("/me")
  public ResponseEntity<AuthResponseCreated> me(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
    return ResponseEntity.ok(service.findByUsername(username));
  }

}
