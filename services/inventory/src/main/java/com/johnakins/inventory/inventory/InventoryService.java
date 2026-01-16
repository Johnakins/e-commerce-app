package com.johnakins.inventory.inventory;

import com.johnakins.inventory.exception.InventoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InventoryService {

    private final InventoryRepository repository;

    public Inventory getInventory(Integer productId) {
        return repository.findByProductId(productId)
                .orElseThrow(() -> new InventoryException("Inventory not found"));
    }

    @Transactional
    public void addStock(Integer productId, Integer quantity) {
        Inventory inventory = repository.findByProductId(productId)
                .orElseGet(() -> new Inventory(productId, 0));
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() + quantity);
        repository.save(inventory);
    }

    @Transactional
    public void reserveStock(Integer productId, Integer quantity) {
        Inventory inventory = getInventory(productId);
        if (inventory.getAvailableQuantity() < quantity) {
            throw new InventoryException("Insufficient stock");
        }
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() - quantity);
        inventory.setReservedQuantity(inventory.getReservedQuantity() + quantity);
        repository.save(inventory);
    }

    @Transactional
    public void releaseStock(Integer productId, Integer quantity) {
        Inventory inventory = getInventory(productId);
        inventory.setReservedQuantity(inventory.getReservedQuantity() - quantity);
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() + quantity);
        repository.save(inventory);
    }

    @Transactional
    public void deductStock(Integer productId, Integer quantity) {
        Inventory inventory = getInventory(productId);
        inventory.setReservedQuantity(inventory.getReservedQuantity() - quantity);
        repository.save(inventory);
    }
}