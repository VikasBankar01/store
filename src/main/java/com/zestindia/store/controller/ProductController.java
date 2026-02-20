package com.zestindia.store.controller;

import org.springframework.web.bind.annotation.*;
import com.zestindia.store.dto.ProductDto;
import com.zestindia.store.dto.ItemDto;
import com.zestindia.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/{id}/items")
    public List<ItemDto> getProductItems(@PathVariable Integer id) {
        return productService.getItemsByProductId(id);
    }
}
