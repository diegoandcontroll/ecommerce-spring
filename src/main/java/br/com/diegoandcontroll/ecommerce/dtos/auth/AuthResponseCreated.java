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

  public AuthResponseCreated(Customer c){
    CustomerObj buildCustomerObj = CustomerObj.builder()
    .id(c.getId())
    .firstname(c.getFirstname())
    .lastname(c.getLastname())
    .imageURl(c.getImageUrl())
    .email(c.getEmail())
    .role(c.getRole())
    .createdAt(c.getCreatedAt())
    .build();
    customer = buildCustomerObj;
  }
}