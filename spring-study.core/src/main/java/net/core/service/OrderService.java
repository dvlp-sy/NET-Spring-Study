package net.core.service;

import net.core.domain.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface OrderService
{
    Optional<Order> createOrder(Long memberId, String itemName, int itemPrice);
}
