package net.core.service;

import net.core.discount.DiscountPolicy;
import net.core.domain.Member;
import net.core.domain.Order;
import net.core.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderServiceImpl implements OrderService
{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
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

    // Test 용도 (귀찮아서 여기 만듦)

    public MemberRepository getMemberRepository()
    {
        return memberRepository;
    }
}
