package br.com.diegoandcontroll.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.diegoandcontroll.ecommerce.domain.Product;
import br.com.diegoandcontroll.ecommerce.domain.User;
import br.com.diegoandcontroll.ecommerce.domain.WishList;
import br.com.diegoandcontroll.ecommerce.dtos.wishlist.WishListRequest;
import br.com.diegoandcontroll.ecommerce.dtos.wishlist.WishListResponse;
import br.com.diegoandcontroll.ecommerce.repositories.ProductRepo;
import br.com.diegoandcontroll.ecommerce.repositories.UserRepo;
import br.com.diegoandcontroll.ecommerce.repositories.WishListRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishListService {
  private final WishListRepo repo;
  private final ProductRepo repoProduct;
  private final UserRepo userRepo;

  public List<WishListResponse> findAllCategoriesNoPaginable() {
    List<WishList> findAll = repo.findAll();
    List<WishListResponse> list = findAll.stream()
        .map(p -> WishListResponse.builder().id(p.getId()).name(p.getUser().getName()).email(p.getUser().getEmail())
            .userId(p.getUser().getId()).product(p.getProduct()).build())
        .toList();
    return list;
  }

  public Page<WishListResponse> findAll(Pageable pageable) {
    Page<WishList> findAll = repo.findAll(pageable);
    return findAll.map(w -> WishListResponse.builder()
        .id(w.getId())
        .email(w.getUser().getEmail())
        .name(w.getUser().getName())
        .userId(w.getUser().getId())
        .product(w.getProduct())
        .build());
  }

  public WishListResponse create(WishListRequest data) {
    Optional<User> userExist = userRepo.findById(data.getUserId());
    Optional<Product> productExist = repoProduct.findById(data.getProductId());
    if (!userExist.isPresent() && !productExist.isPresent()) {
      new UsernameNotFoundException("Product or User not found");
    }
    User user = userExist.get();
    Product product = productExist.get();

    var wishlist = WishList.builder()
        .user(user)
        .product(product)
        .build();

    WishList wishListSaved = repo.save(wishlist);

    return WishListResponse.builder().id(wishListSaved.getId()).userId(user.getId()).name(user.getName())
        .email(user.getEmail()).product(product).build();
  }

  public List<WishListResponse> findAllByUserId(UUID userId) {
    Optional<User> findById = userRepo.findById(userId);
    if (!findById.isPresent()) {
      new UsernameNotFoundException("User not found");
    }
    User user = findById.get();
    List<WishList> findAllByUser = repo.findAllByUser(user);

    return findAllByUser.stream().map(w -> WishListResponse.builder().id(w.getId()).name(w.getUser().getName())
        .email(w.getUser().getEmail()).userId(userId).product(w.getProduct()).build()).toList();
  }
}
