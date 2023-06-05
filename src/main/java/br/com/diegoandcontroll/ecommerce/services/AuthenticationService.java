package br.com.diegoandcontroll.ecommerce.services;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.diegoandcontroll.ecommerce.config.JwtService;
import br.com.diegoandcontroll.ecommerce.domain.Customer;
import br.com.diegoandcontroll.ecommerce.domain.Role;
import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthRequest;
import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthRequestCreated;
import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthResponse;
import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthResponseCreated;
import br.com.diegoandcontroll.ecommerce.exceptions.CustomException;
import br.com.diegoandcontroll.ecommerce.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final CustomerRepo repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public Customer findById(UUID userId) {
    Optional<Customer> userExist = repository.findById(userId);
    if (!userExist.isPresent()) {
      throw new CustomException("CUSTOMER NOT FOUND", HttpStatus.NOT_FOUND, "/api/v1/auth");
    }
    return userExist.get();
  }
  
  public AuthResponseCreated findByUsername(String username) {
    Optional<Customer> customerExist = repository.findByEmail(username);
    if (!customerExist.isPresent()) {
      throw new CustomException("CUSTOMER NOT FOUND", HttpStatus.NOT_FOUND, "/api/v1/auth");
    }
    var customer = customerExist.get();
    var authResponseCreated = new AuthResponseCreated(customer);
    return authResponseCreated;
  }

  @Transactional
  public AuthResponseCreated register(AuthRequestCreated request) {
    if (request.getEmail().toLowerCase().equals("lukasxdp@gmail.com")){
      var customer = Customer.builder()
          .email(request.getEmail())
          .firstname(request.getFirstname())
          .lastname(request.getLastname())
          .imageUrl(request.getImageUrl())
          .password(passwordEncoder.encode(request.getPassword()))
          .createdAt(new Date())
          .role(Role.ADMIN)
          .build();
      var savedUser = repository.save(customer);
      var authResponseCreated = new AuthResponseCreated(savedUser);
      return authResponseCreated;
    } else {
      var customer = Customer.builder()
          .email(request.getEmail())
          .firstname(request.getFirstname())
          .lastname(request.getLastname())
          .imageUrl(request.getImageUrl())
          .password(passwordEncoder.encode(request.getPassword()))
          .createdAt(new Date())
          .role(Role.USER)
          .build();
      var savedUser = repository.save(customer);
      var authResponseCreated = new AuthResponseCreated(savedUser);
      return authResponseCreated;
    }

  }

  public AuthResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()));
    var customer = repository.findByEmail(request.getEmail())
        .orElseThrow(() -> new CustomException("NOT FOUND CUSTOMER", HttpStatus.NOT_FOUND, "/api/v1/auth"));
    var jwtToken = jwtService.generateToken(customer);
    return new AuthResponse(customer, jwtToken);
  }

  public Page<AuthResponseCreated> findAll(Pageable pageable) {
    Page<Customer> customers = repository.findAll(pageable);
    return customers.map(c -> new AuthResponseCreated(c));
  }

  

}