package br.com.diegoandcontroll.ecommerce.dtos.auth;

import java.util.UUID;

import br.com.diegoandcontroll.ecommerce.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerObj {
  private UUID id;

  private String firstname;

  private String lastname;

  private String email;

  private String imageURl;
  
  private Role role;
}
