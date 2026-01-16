package com.johnakins.product.inventory;

//public interface InventoryClient {
//}

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "inventory-service",
       url = "${application.config.inventory-url}"
)
public interface InventoryClient {

    @PostMapping("/reserve")
    void reserveStock(@RequestParam("productId") Integer productId,
                      @RequestParam("quantity") Integer quantity);

    @PostMapping("/release")
    void releaseStock(@RequestParam("productId") Integer productId,
                      @RequestParam("quantity") Integer quantity);
}
