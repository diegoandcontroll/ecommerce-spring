package br.com.diegoandcontroll.ecommerce.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegoandcontroll.ecommerce.domain.User;
import br.com.diegoandcontroll.ecommerce.domain.WishList;

public interface WishListRepo extends JpaRepository<WishList, UUID>{
  List<WishList> findAllByUser(User user);
}
