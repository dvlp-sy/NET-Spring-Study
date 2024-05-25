package net.jpa.service;

import lombok.RequiredArgsConstructor;
import net.jpa.domain.*;
import net.jpa.domain.item.Item;
import net.jpa.repository.ItemRepository;
import net.jpa.repository.MemberRepository;
import net.jpa.repository.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrdersService {

    private final MemberRepository memberRepository;
    private final OrdersRepository ordersRepository;
    private final ItemRepository itemRepository;

    /**
     * 상품 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, Long count) {

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Orders orders = Orders.createOrders(member, delivery, orderItem);

        ordersRepository.save(orders);
        return orders.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        Orders orders = ordersRepository.findOne(orderId);
        orders.cancel();
    }

}
