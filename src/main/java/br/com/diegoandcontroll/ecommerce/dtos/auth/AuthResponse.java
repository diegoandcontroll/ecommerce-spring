package br.com.diegoandcontroll.ecommerce.dtos.auth;

import br.com.diegoandcontroll.ecommerce.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
  private CustomerObj customer;
  private String access_token;
  

  public AuthResponse(Customer c, String token){
    customer.setEmail(c.getEmail());
    customer.setFirstname(c.getFirstname());
    customer.setLastname(c.getLastname());
    customer.setImageURl(c.getImageUrl());
    customer.setRole(c.getRole());
    access_token = token; 
  }
}
