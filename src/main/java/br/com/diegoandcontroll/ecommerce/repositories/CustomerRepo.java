package br.com.diegoandcontroll.ecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegoandcontroll.ecommerce.domain.Customer;


public interface CustomerRepo extends JpaRepository<Customer, UUID>{
  Optional<Customer> findByEmail(String email);
  
  Optional<Customer> findById(UUID id);
}
