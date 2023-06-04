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
public class AuthResponseCreated {
  
  private CustomerObj customer;

  public AuthResponseCreated(Customer u){
    customer.setEmail(u.getEmail());
    customer.setFirstname(u.getFirstname());
    customer.setLastname(u.getLastname());
    customer.setImageURl(u.getImageUrl());
    customer.setRole(u.getRole());
     
  }
}