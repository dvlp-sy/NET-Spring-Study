package net.jpa.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.jpa.domain.Category;
import net.jpa.domain.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item
{
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private Long price;

    private Long stockQuantity;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();
}
