package com.zestindia.store.dto;

import jakarta.validation.constraints.NotNull;

public class ItemDto {
    private Integer id;

    @NotNull
    private Integer quantity;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
