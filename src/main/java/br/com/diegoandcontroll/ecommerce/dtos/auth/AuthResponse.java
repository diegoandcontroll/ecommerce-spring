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
    access_token = token; 
  }
}
