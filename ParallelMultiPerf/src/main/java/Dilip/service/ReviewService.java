package Dilip.service;

import Dilip.domain.Review;

import static Dilip.util.CommonUtil.delay;

public class ReviewService {
  
  public Review retrieveReviews(String productId) {
    delay(1000);
    return new Review(200, 4.5);
  }
}
