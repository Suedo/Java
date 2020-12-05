package Dilip.service;

import Dilip.domain.Product;
import Dilip.domain.ProductInfo;
import Dilip.domain.Review;
import lombok.extern.log4j.Log4j2;

import static Dilip.util.CommonUtil.stopWatch;

@Log4j2
public class ProductService {
  private final ProductInfoService productInfoService;
  private final ReviewService reviewService;
  
  public ProductService(ProductInfoService productInfoService, ReviewService reviewService) {
    this.productInfoService = productInfoService;
    this.reviewService = reviewService;
  }
  
  public Product retrieveProductDetails(String productId) {
    stopWatch.start();
    
    ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking call
    Review review = reviewService.retrieveReviews(productId); // blocking call
    
    stopWatch.stop();
    log.info("Total Time Taken : " + stopWatch.getTime());
    return new Product(productId, productInfo, review);
  }
  
  public static void main(String[] args) {
    
    ProductInfoService productInfoService = new ProductInfoService();
    ReviewService reviewService = new ReviewService();
    ProductService productService = new ProductService(productInfoService, reviewService);
    String productId = "ABC123";
    Product product = productService.retrieveProductDetails(productId);
    log.info("Product is " + product);
    
  }
}
