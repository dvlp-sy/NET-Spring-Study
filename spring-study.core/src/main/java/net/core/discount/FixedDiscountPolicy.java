package net.core.discount;

import net.core.domain.Grade;
import net.core.domain.Member;

public class FixedDiscountPolicy implements DiscountPolicy
{
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price)
    {
        if (member.getGrade() == Grade.VIP)
            return discountFixAmount;
        else
            return 0;
    }
}
