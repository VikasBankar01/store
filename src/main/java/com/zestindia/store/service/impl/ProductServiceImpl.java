package com.zestindia.store.service.impl;

import com.zestindia.store.dto.ProductDto;
import com.zestindia.store.dto.ItemDto;
import com.zestindia.store.entity.Product;
import com.zestindia.store.entity.Item;
import com.zestindia.store.repository.ProductRepository;
import com.zestindia.store.repository.ItemRepository;
import com.zestindia.store.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        List<ProductDto> dtos = page.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return toDto(product);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = toEntity(productDto);

        // Log before saving
        logger.info("Before save, Product id: {}, name: {}", product.getId(), product.getProductName());

        // Save product (items will cascade automatically)
        product = productRepository.save(product);

        logger.info("After save, Product id: {}, name: {}", product.getId(), product.getProductName());

        return toDto(product);
    }

    @Override
    public ProductDto updateProduct(Integer id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        product.setProductName(productDto.getProductName());

        // Optional: handle items update if required
        if (productDto.getItems() != null) {
            product.getItems().clear();
            for (ItemDto itemDto : productDto.getItems()) {
                Item item = new Item();
                item.setQuantity(itemDto.getQuantity());
                item.setProduct(product);
                product.getItems().add(item);
            }
        }

        product = productRepository.save(product);
        return toDto(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ItemDto> getItemsByProductId(Integer productId) {
        List<Item> items = itemRepository.findByProductId(productId);
        return items.stream().map(this::toItemDto).collect(Collectors.toList());
    }

    private ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setCreatedBy(product.getCreatedBy());
        dto.setCreatedOn(product.getCreatedOn());
        dto.setModifiedBy(product.getModifiedBy());
        dto.setModifiedOn(product.getModifiedOn());

        List<ItemDto> itemDtos = product.getItems().stream()
                .map(this::toItemDto)
                .collect(Collectors.toList());
        dto.setItems(itemDtos);

        return dto;
    }

    private ItemDto toItemDto(Item item) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        return dto;
    }

    private Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setProductName(dto.getProductName());

        List<Item> items = new ArrayList<>();
        if (dto.getItems() != null) {
            for (ItemDto itemDto : dto.getItems()) {
                Item item = new Item();
                item.setQuantity(itemDto.getQuantity());
                item.setProduct(product); // important for bidirectional
                items.add(item);
            }
        }
        product.setItems(items);

        return product;
    }
}