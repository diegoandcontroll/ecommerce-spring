package br.com.diegoandcontroll.ecommerce.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestCreated {
  private String firstname;
  private String lastname;
  private String imageUrl;
  private String email;
  private String password;
}
