package br.com.diegoandcontroll.ecommerce.dtos.auth;

import java.util.Date;
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
public class CustomerObj {
  private UUID id;

  private String firstname;

  private String lastname;

  private String email;

  private String imageURl;
  
  private Role role;

  private Date createdAt;
}
