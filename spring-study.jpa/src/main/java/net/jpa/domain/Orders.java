package net.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Orders
{
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    /* 연관관계 메서드 */
    private void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    private void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrders(this);
    }

    private void setDelivery(Delivery delivery) {
        this.delivery  = delivery;
        delivery.setOrders(this);
    }

    /* 생성 메서드 */
    public static Orders createOrders(Member member, Delivery delivery, OrderItem... orderItems) {

        Orders orders = new Orders();
        orders.setMember(member);
        orders.setDelivery(delivery);

        for (OrderItem orderItem : orderItems) {
            orders.addOrderItem(orderItem);
        }
        orders.setStatus(OrderStatus.ORDER);
        orders.setOrderDate(LocalDateTime.now());
        return orders;
    }

    /* 비즈니스 로직 */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP)
            throw new IllegalStateException("이미 배송 완료된 상품은 취소가 불가능합니다.");

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    public Long getTotalPrice() {
        Long totalPrice = 0L;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
