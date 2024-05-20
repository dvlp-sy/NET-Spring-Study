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
}
