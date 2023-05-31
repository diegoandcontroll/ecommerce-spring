package br.com.diegoandcontroll.ecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegoandcontroll.ecommerce.domain.WishList;

public interface WishListRepo extends JpaRepository<WishList, UUID>{
  Optional<WishList> findByUserId(UUID userId);
}
