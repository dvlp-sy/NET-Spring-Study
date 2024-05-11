package net.core.discount;

import net.core.domain.Grade;
import net.core.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy
{
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price)
    {
        if (member.getGrade() == Grade.VIP)
            return price * discountPercent / 100;
        else
            return 0;
    }
}
