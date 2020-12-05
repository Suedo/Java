package Dilip.service;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.CompletableFuture;

import static Dilip.util.CommonUtil.delay;

@Log4j2
public class HelloWorldService {
  
  public String helloWorld() {
    delay(1000);
    log.info("inside helloWorld");
    return "hello world";
  }
  
  public String hello() {
    delay(1000);
    log.info("inside hello");
    return "hello";
  }
  
  public String world() {
    delay(1000);
    log.info("inside world");
    return " world!";
  }
  
  public CompletableFuture<String> worldFuture(String input) {
    return CompletableFuture.supplyAsync(() -> {
      delay(1000);
      return input + " world!";
    });
  }
  
}