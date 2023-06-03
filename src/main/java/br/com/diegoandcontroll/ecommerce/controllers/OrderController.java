package br.com.diegoandcontroll.ecommerce.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import br.com.diegoandcontroll.ecommerce.dtos.order.OrderRequest;
import br.com.diegoandcontroll.ecommerce.dtos.order.StripeResponse;
import br.com.diegoandcontroll.ecommerce.services.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<OrderRequest> orderListItems) throws StripeException{
    Session session = orderService.createSession(orderListItems);
    var response = new StripeResponse(session.getId());
    return ResponseEntity.ok(response);
  }
}
