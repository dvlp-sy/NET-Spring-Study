package net.core.discount;

import net.core.domain.Member;

public interface DiscountPolicy
{
    int discount(Member member, int price);
}
