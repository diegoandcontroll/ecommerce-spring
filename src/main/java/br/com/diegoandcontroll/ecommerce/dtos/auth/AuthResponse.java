package br.com.diegoandcontroll.ecommerce.dtos.auth;

import java.util.UUID;

import br.com.diegoandcontroll.ecommerce.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
  private UUID id;
  private String name;
  private String email;
  private Role role;
  private String access_token;
}
