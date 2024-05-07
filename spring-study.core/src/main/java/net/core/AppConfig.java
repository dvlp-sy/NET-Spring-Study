package net.core;

import net.core.discount.DiscountPolicy;
import net.core.discount.FixedDiscountPolicy;
import net.core.discount.RateDiscountPolicy;
import net.core.repository.MemberRepository;
import net.core.repository.MemoryMemberRepository;
import net.core.service.MemberService;
import net.core.service.MemberServiceImpl;
import net.core.service.OrderService;
import net.core.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig
{
    @Bean
    public MemberRepository memberRepository()
    {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy()
    {
        return new FixedDiscountPolicy();
    }

    @Bean
    public MemberService memberService()
    {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService()
    {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
