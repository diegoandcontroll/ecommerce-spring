package br.com.diegoandcontroll.ecommerce.utils;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.diegoandcontroll.ecommerce.domain.Customer;
import br.com.diegoandcontroll.ecommerce.exceptions.CustomException;
import br.com.diegoandcontroll.ecommerce.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerUtils {
  private final CustomerRepo customerRepo;
  public String getRoleUserLogged() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      return userDetails.getAuthorities().iterator().next().getAuthority();
    }

    return null;
  }

  public String getUsernameCustomerLogged() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return securityContext.getAuthentication().getName();
  }

  public void verifyRole(String role, String path) {
    if (role != "ADMIN") {
      throw new CustomException("Forbidden: You don't have permission to access this resource.", HttpStatus.FORBIDDEN, path);
    }
    return;
  }

  public Customer getCutomerLogger(String path){
    String username = getUsernameCustomerLogged();
     Optional<Customer> customer = customerRepo.findByEmail(username);
     if(!customer.isPresent()){
      throw new CustomException("CUSTOMER NOT FOUND", HttpStatus.NOT_FOUND, path);
     }
    return customer.get();
  }

}
