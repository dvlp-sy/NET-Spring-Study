package net.core.service;

import net.core.discount.DiscountPolicy;
import net.core.discount.FixedDiscountPolicy;
import net.core.domain.Grade;
import net.core.domain.Member;
import net.core.domain.Order;
import net.core.repository.MemberRepository;
import net.core.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixedDiscountPolicy();
    private final MemberService memberService = new MemberServiceImpl(memberRepository);
    private final OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);

    @Test
    void createOrder()
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