package net.jpa.service;

import jakarta.persistence.EntityManager;
import net.jpa.domain.Address;
import net.jpa.domain.Member;
import net.jpa.domain.OrderStatus;
import net.jpa.domain.Orders;
import net.jpa.domain.item.Book;
import net.jpa.domain.item.Item;
import net.jpa.exception.NotEnoughStockException;
import net.jpa.repository.OrdersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrdersServiceTest {

    @Autowired EntityManager em;

    @Autowired OrdersService ordersService;
    @Autowired OrdersRepository ordersRepository;
    @Autowired MemberService memberService;

    @Test
    @DisplayName("상품 주문")
    public void orderItem() {

        // Given
        Member member = createMember("memberA");
        Item item = createBook("JPA", 10000L, 20L);
        Long orderCount = 2L;

        // When
        Long orderId = ordersService.order(member.getId(), item.getId(), orderCount);

        // Then
        Orders getOrder = ordersRepository.findOne(orderId);
        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(getOrder.getOrderItems().size()).isEqualTo(1L);
        assertThat(getOrder.getTotalPrice()).isEqualTo(10000L*2L);
        assertThat(item.getStockQuantity()).isEqualTo(18L);
    }

    @Test
    @DisplayName("상품 주문_재고 초과")
    public void overStock() {

        // Given
        Member member = createMember("memberB");
        Item item = createBook("JPA", 10000L, 2L);
        Long orderCount = 10L;

        // Then
        assertThrows(NotEnoughStockException.class, () -> ordersService.order(member.getId(), item.getId(), orderCount));

    }

    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, Long price, Long stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }
}