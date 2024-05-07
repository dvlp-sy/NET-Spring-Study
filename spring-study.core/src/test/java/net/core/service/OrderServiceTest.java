package net.core.service;

import net.core.AppConfig;
import net.core.domain.Grade;
import net.core.domain.Member;
import net.core.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    private MemberService memberService;
    private OrderService orderService;

    @BeforeEach
    public void beforeEach()
    {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    public void createOrder()
    {
        // given
        Member member = new Member(1L, "Lala", Grade.VIP);

        // when
        memberService.join(member);
        Order order = orderService.createOrder(1L, "itemA", 10000).orElse(null);

        // then
        Assertions.assertThat(order).isNotNull();
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}