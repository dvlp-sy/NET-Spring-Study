package net.core.service;

import net.core.discount.DiscountPolicy;
import net.core.domain.Member;
import net.core.domain.Order;
import net.core.repository.MemberRepository;

import java.util.Optional;

public class OrderServiceImpl implements OrderService
{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy)
    {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Optional<Order> createOrder(Long memberId, String itemName, int itemPrice)
    {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        int discountPrice = discountPolicy.discount(member, itemPrice);
        return Optional.of(new Order(memberId, itemName, itemPrice, discountPrice));
    }
}
