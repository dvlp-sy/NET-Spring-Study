package net.core.service;

import net.core.AppConfig;
import net.core.domain.Grade;
import net.core.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest
{
    private MemberService memberService;

    @BeforeEach
    public void beforeEach()
    {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    public void join()
    {
        // given
        Member member = new Member(1L, "Gildong,", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        // then
        assertThat(member).isEqualTo(findMember);

    }

    @Test
    public void findMember()
    {
        // given
        Member member = new Member(2L, "Nana", Grade.BASIC);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        // then
        assertThat(member).isEqualTo(findMember);
    }
}