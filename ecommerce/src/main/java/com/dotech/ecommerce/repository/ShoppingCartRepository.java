package com.dotech.ecommerce.repository;

import com.dotech.ecommerce.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>  {
}
