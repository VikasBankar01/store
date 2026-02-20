package com.zestindia.store.service;

import com.zestindia.store.dto.ProductDto;
import com.zestindia.store.dto.ItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    Page<ProductDto> getAllProducts(Pageable pageable);
    ProductDto getProductById(Integer id);
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Integer id, ProductDto productDto);
    void deleteProduct(Integer id);
    List<ItemDto> getItemsByProductId(Integer productId);
}
