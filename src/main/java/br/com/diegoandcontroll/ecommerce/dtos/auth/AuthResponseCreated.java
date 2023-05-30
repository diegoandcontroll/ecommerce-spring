package br.com.diegoandcontroll.ecommerce.dtos.auth;

import java.util.UUID;

import br.com.diegoandcontroll.ecommerce.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseCreated {
  private UUID id;
  private String name;
  private String email;
  private String role;
  private String access_token;

  public AuthResponseCreated(User u){
    id = u.getId();
    name = u.getName();
    email = u.getEmail();
    role = u.getRole().name(); 
  }
}