package com.johnakins.inventory.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService service;

    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> getInventory(@PathVariable Integer productId) {
        return ResponseEntity.ok(service.getInventory(productId));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addStock(@RequestParam Integer productId,
                                        @RequestParam Integer quantity) {
        service.addStock(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reserve")
    public ResponseEntity<Void> reserveStock(@RequestParam Integer productId,
                                            @RequestParam Integer quantity) {
        service.reserveStock(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/release")
    public ResponseEntity<Void> releaseStock(@RequestParam Integer productId,
                                            @RequestParam Integer quantity) {
        service.releaseStock(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deduct")
    public ResponseEntity<Void> deductStock(@RequestParam Integer productId,
                                           @RequestParam Integer quantity) {
        service.deductStock(productId, quantity);
        return ResponseEntity.ok().build();
    }
}