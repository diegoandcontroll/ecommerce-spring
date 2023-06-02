package br.com.diegoandcontroll.ecommerce.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

  public User findById(UUID userId){
    Optional<User> userExist = repository.findById(userId);
    if(!userExist.isPresent()){
      new UsernameNotFoundException("User not found");
    }
    return userExist.get();
  }

  public AuthResponseCreated findByUsername(String username){
    Optional<User> userExist = repository.findByEmail(username);
    if(!userExist.isPresent()){
      new UsernameNotFoundException("User not found");
    }
    var user = userExist.get();
    var authResponseCreated = AuthResponseCreated.builder()
        .id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .role(user.getRole().name())
        .build();
    return authResponseCreated;
  }
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