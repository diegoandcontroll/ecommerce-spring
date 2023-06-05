package br.com.diegoandcontroll.ecommerce.utils;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.diegoandcontroll.ecommerce.exceptions.CustomException;

@Component
public class CustomerUtils {
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

}
