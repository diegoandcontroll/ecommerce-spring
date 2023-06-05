package br.com.diegoandcontroll.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.diegoandcontroll.ecommerce.domain.Customer;
import br.com.diegoandcontroll.ecommerce.domain.Product;
import br.com.diegoandcontroll.ecommerce.domain.WishList;
import br.com.diegoandcontroll.ecommerce.dtos.wishlist.WishListRequest;
import br.com.diegoandcontroll.ecommerce.dtos.wishlist.WishListResponse;
import br.com.diegoandcontroll.ecommerce.repositories.CustomerRepo;
import br.com.diegoandcontroll.ecommerce.repositories.ProductRepo;
import br.com.diegoandcontroll.ecommerce.repositories.WishListRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishListService {
  private final WishListRepo repo;
  private final ProductRepo repoProduct;
  private final CustomerRepo customerRepo;

  public List<WishListResponse> findAllCategoriesNoPaginable() {
    List<WishList> findAll = repo.findAll();
    List<WishListResponse> list = findAll.stream()
        .map(p -> new WishListResponse(p)).toList();
    return list;
  }

  public Page<WishListResponse> findAll(Pageable pageable) {
    Page<WishList> findAll = repo.findAll(pageable);
    return findAll.map(w -> new WishListResponse(w));
  }

  public WishListResponse create(WishListRequest data) {
    Optional<Customer> customerExist = customerRepo.findById(data.getUserId());
    Optional<Product> productExist = repoProduct.findById(data.getProductId());
    if (!customerExist.isPresent() && !productExist.isPresent()) {
      new UsernameNotFoundException("Product or User not found");
    }
    Customer user = customerExist.get();
    Product product = productExist.get();

    var wishlist = WishList.builder()
        .customer(user)
        .product(product)
        .build();

    WishList wishListSaved = repo.save(wishlist);

    return new WishListResponse(wishListSaved);
  }

  public List<WishListResponse> findAllByUserId(UUID userId) {
    Optional<Customer> findById = customerRepo.findById(userId);
    if (!findById.isPresent()) {
      new UsernameNotFoundException("User not found");
    }
    Customer customer = findById.get();
    List<WishList> findAllByUser = repo.findAllByCustomer(customer);

    return findAllByUser.stream().map(w -> new WishListResponse(w)).toList();
  }
}
