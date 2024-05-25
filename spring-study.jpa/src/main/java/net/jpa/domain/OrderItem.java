package net.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.jpa.domain.item.Item;

@Entity
@Getter
@Setter
public class OrderItem
{
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    private Long orderPrice;

    private Long count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    /* 생성 메서드 */
    public static OrderItem createOrderItem(Item item, Long orderPrice, Long count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;
    }

    /* 비즈니스 로직 */
    public void cancel() {
        this.item.addStock(count);
    }

    public Long getTotalPrice() {
        return getOrderPrice() * count;
    }

}
