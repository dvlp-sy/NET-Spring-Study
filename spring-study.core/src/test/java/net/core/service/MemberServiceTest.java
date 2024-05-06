package net.core.service;

import net.core.domain.Grade;
import net.core.domain.Member;
import net.core.repository.MemberRepository;
import net.core.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest
{
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberService memberService = new MemberServiceImpl(memberRepository);

    @Test
    void join()
    {
        // given
        Member member = new Member(1L, "Gildong,", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        // then
        Assertions.assertThat(member).isEqualTo(findMember);

    }

    @Test
    void findMember()
    {
        // given
        Member member = new Member(2L, "Nana", Grade.BASIC);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}