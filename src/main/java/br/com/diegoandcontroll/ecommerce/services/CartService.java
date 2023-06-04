package br.com.diegoandcontroll.ecommerce.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.diegoandcontroll.ecommerce.domain.Cart;
import br.com.diegoandcontroll.ecommerce.domain.Customer;
import br.com.diegoandcontroll.ecommerce.domain.Product;
import br.com.diegoandcontroll.ecommerce.dtos.cart.CartRequest;
import br.com.diegoandcontroll.ecommerce.dtos.cart.CartResponse;
import br.com.diegoandcontroll.ecommerce.dtos.cart.CartResponseAll;
import br.com.diegoandcontroll.ecommerce.dtos.category.ProductItem;
import br.com.diegoandcontroll.ecommerce.exceptions.CustomException;
import br.com.diegoandcontroll.ecommerce.repositories.CartRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepo repo;
  private final ProductService pService;
  private final AuthenticationService aService;

  public CartResponse createCart(CartRequest cartRequest) {
    Customer customer = aService.findById(cartRequest.getUserId());
    Product product = pService.findProductById(cartRequest.getProductId());
    List<ProductItem> listProductsItems = new ArrayList<>();
    var cart = Cart.builder()
        .id(cartRequest.getId() != null ? cartRequest.getId() : UUID.randomUUID())
        .product(product)
        .quantity(cartRequest.getQuantity())
        .customer(customer)
        .createdAt(new Date())
        .build();
    Cart save = repo.save(cart);
    ProductItem productItem = new ProductItem(save);
    listProductsItems.add(productItem);
    return CartResponse.builder()
        .products(listProductsItems)
        .total(cartRequest.getQuantity() * save.getProduct().getPrice())
        .build();
  }

  public List<CartResponse> findAllByUserId(UUID userId) {
    Customer user = aService.findById(userId);
    List<Cart> cartUser = repo.findAllByUser(user);
    List<ProductItem> cartItems = new ArrayList<>();
    List<CartResponse> list = new ArrayList<>();
    double totalCost = 0;
    for (Cart cart : cartUser) {
      ProductItem cartItem = new ProductItem(cart);
      totalCost += cartItem.getQuantity() * cart.getProduct().getPrice();
      cartItems.add(cartItem);
    }

    CartResponse response = CartResponse.builder()
        .total(totalCost)
        .products(cartItems)
        .build();

    list.add(response);
    return list;

  }

  public List<CartResponseAll> findAllCartsNoPaginable() {
    List<Cart> findAll = repo.findAll();
    List<CartResponseAll> list = findAll.stream().map(i -> new CartResponseAll(i)).toList();
    return list;
  }

  public Page<CartResponse> findAll(Pageable pageable) {
    return null;
  }

  public String deleteCart(UUID cartId, Customer customer){
    Optional<Cart> cartExist = repo.findById(cartId);

    if(!cartExist.isPresent()){
      throw new CustomException("NOT FOUND CART", HttpStatus.NOT_FOUND, "/api/v1/cart");
    }
    Cart cart = cartExist.get();

    if(cart.getCustomer() != customer){
      throw new CustomException("NOT FOUND CART", HttpStatus.BAD_REQUEST, "/api/v1/cart");
    }
    repo.delete(cart);
    return "DELETED CART";
  }

}
