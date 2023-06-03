package br.com.diegoandcontroll.ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import br.com.diegoandcontroll.ecommerce.dtos.order.OrderRequest;

@Service
public class OrderService {
  @Value("${BASE_URL}")
  private  String baseUrl;

  @Value("${STRIPE_SECRET_KEY}")
  private  String apikey;

  public Session createSession(List<OrderRequest> orderListItems) throws StripeException {
    String successUrl = baseUrl + "payment/success";
    String failureUrl = baseUrl + "payment/failure";
    Stripe.apiKey = apikey;
    List<SessionCreateParams.PaymentMethodType> listPaymentTypes = new ArrayList<>();

    listPaymentTypes.add(SessionCreateParams.PaymentMethodType.BOLETO);
    listPaymentTypes.add(SessionCreateParams.PaymentMethodType.CARD);
    List<SessionCreateParams.LineItem> sessionListItems = new ArrayList<>();
    for (OrderRequest orderItem : orderListItems) {
      sessionListItems.add(createSessionLineItem(orderItem));
    }
    var params = SessionCreateParams.builder()
    .addAllPaymentMethodType(listPaymentTypes)
    .setMode(SessionCreateParams.Mode.PAYMENT)
    .setCancelUrl(failureUrl)
    .setSuccessUrl(successUrl)
    .addAllLineItem(sessionListItems)
    .build();

    return Session.create(params);
  }

  private SessionCreateParams.LineItem createSessionLineItem(OrderRequest orderItem) {
    return SessionCreateParams.LineItem.builder()
        .setPriceData(createPriceData(orderItem))
        .setQuantity(Long.parseLong(String.valueOf(orderItem.getQuantity())))
        .build();
  }

  private SessionCreateParams.LineItem.PriceData createPriceData(OrderRequest orderItem) {
    return SessionCreateParams.LineItem.PriceData.builder()
        .setCurrency("brl")
        .setUnitAmount((long) orderItem.getPrice() * 100)
        .setProductData(
            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(orderItem.getProductName())
                .build())
        .build();
  }

}
