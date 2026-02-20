package com.zestindia.store.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

public class ProductDto {
    private Integer id;

    @NotBlank
    private String productName;

    private Integer createdBy;

    private Instant createdOn;
    private Integer modifiedBy;
    private Instant modifiedOn;
    private List<ItemDto> items;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Integer getCreatedBy() { return createdBy; }
    public void setCreatedBy(Integer createdBy) { this.createdBy = createdBy; }
    public Instant getCreatedOn() { return createdOn; }
    public void setCreatedOn(Instant createdOn) { this.createdOn = createdOn; }
    public Integer getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(Integer modifiedBy) { this.modifiedBy = modifiedBy; }
    public Instant getModifiedOn() { return modifiedOn; }
    public void setModifiedOn(Instant modifiedOn) { this.modifiedOn = modifiedOn; }
    public List<ItemDto> getItems() { return items; }
    public void setItems(List<ItemDto> items) { this.items = items; }
}
