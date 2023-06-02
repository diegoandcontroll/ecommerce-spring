package br.com.diegoandcontroll.ecommerce.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegoandcontroll.ecommerce.domain.Cart;
import br.com.diegoandcontroll.ecommerce.domain.User;

public interface CartRepo extends JpaRepository<Cart, UUID>{
  List<Cart> findAllByUser(User user);
}
