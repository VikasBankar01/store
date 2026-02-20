package com.zestindia.store.repository;

import com.zestindia.store.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByProductId(Integer productId);
}
