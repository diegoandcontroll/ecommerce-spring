package br.com.diegoandcontroll.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.diegoandcontroll.ecommerce.domain.Customer;
import br.com.diegoandcontroll.ecommerce.domain.Product;
import br.com.diegoandcontroll.ecommerce.domain.WishList;
import br.com.diegoandcontroll.ecommerce.dtos.wishlist.WishListResponse;
import br.com.diegoandcontroll.ecommerce.exceptions.CustomException;
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

  public WishListResponse create(UUID product, Customer user) {
    Optional<Product> productExist = repoProduct.findById(product);
    if (!productExist.isPresent()) {
      throw new CustomException("NOT FOUND PRODUCT", HttpStatus.NOT_FOUND, "/api/v1/wishlist");
    }
    Product productFind = productExist.get();

    var wishlist = WishList.builder()
        .customer(user)
        .product(productFind)
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

  public String remove(UUID wishlistId, Customer customer) {
    Optional<WishList> wishlist = repo.findById(wishlistId);
    if(!wishlist.isPresent()){
      throw new CustomException("NOT FOUND WISH LIST", HttpStatus.NOT_FOUND, "/api/v1/wishlist");
    }
    WishList existWishlist = wishlist.get();
    if(existWishlist.getCustomer() != customer){
      throw new CustomException("BAD REQUEST", HttpStatus.BAD_REQUEST, "/api/v1/wishlist");
    }
    repo.delete(existWishlist);
    return "ITEM REMOVED TO WISHLIST";
  }
}
