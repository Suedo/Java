package Dilip.service;

import Dilip.domain.checkout.CartItem;

import static Dilip.util.CommonUtil.delay;

public class PriceValidatorService {
  
  public boolean isCartItemInvalid(CartItem cartItem) {
    int cartId = cartItem.getItemId();
    delay(500);
    return cartId == 7 || cartId == 9 || cartId == 11;
  }
}