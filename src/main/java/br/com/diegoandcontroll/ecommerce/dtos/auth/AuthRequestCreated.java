package br.com.diegoandcontroll.ecommerce.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestCreated {
  private String name;
  private String email;
  private String password;
}
