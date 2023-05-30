package br.com.diegoandcontroll.ecommerce.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.diegoandcontroll.ecommerce.config.JwtService;
import br.com.diegoandcontroll.ecommerce.domain.Role;
import br.com.diegoandcontroll.ecommerce.domain.User;
import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthRequest;
import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthRequestCreated;
import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthResponse;
import br.com.diegoandcontroll.ecommerce.dtos.auth.AuthResponseCreated;
import br.com.diegoandcontroll.ecommerce.repositories.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepo repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthResponseCreated register(AuthRequestCreated request) {
    var user = User.builder()
        .email(request.getEmail())
        .name(request.getName())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
    var savedUser = repository.save(user);
    var authResponseCreated = AuthResponseCreated.builder()
        .id(savedUser.getId())
        .email(savedUser.getEmail())
        .name(savedUser.getName())
        .role(savedUser.getRole().name())
        .build();
    return authResponseCreated;
    
  }

  public AuthResponse authenticate(AuthRequest request) {
  authenticationManager.authenticate(
  new UsernamePasswordAuthenticationToken(
  request.getEmail(),
  request.getPassword()
  )
  );
  var user = repository.findByEmail(request.getEmail())
  .orElseThrow();
  var jwtToken = jwtService.generateToken(user);
  return AuthResponse.builder()
  .id(user.getId())
  .name(user.getName())
  .email(user.getEmail())
  .role(user.getRole())
  .access_token(jwtToken)
  .build();
  }
}