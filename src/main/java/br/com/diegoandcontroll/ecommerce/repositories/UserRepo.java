package br.com.diegoandcontroll.ecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegoandcontroll.ecommerce.domain.User;


public interface UserRepo extends JpaRepository<User, UUID>{
  Optional<User> findByEmail(String email);
  
  Optional<User> findById(UUID id);
}
