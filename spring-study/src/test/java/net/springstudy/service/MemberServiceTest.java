package net.springstudy.service;

import net.springstudy.domain.Member;
import net.springstudy.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    MemberService memberService = new MemberService(memoryMemberRepository);

    @BeforeEach
    public void beforeEach()
    {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    void afterEach()
    {
        memoryMemberRepository.clearStore();
    }

    @Test
    void join()
    {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long result = memberService.join(member);

        // then
        Member findMember = memberService.findOne(member.getId());
        assertThat(findMember.getId()).isEqualTo(result);
    }

    @Test
    void duplicatedMemberException()
    {
        // given
        Member member1 = new Member();
        member1.setName("abcd");
        Member member2 = new Member();
        member2.setName("abcd");

        // when & then
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 얘외가 터져야 성공
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

/*
        try
        {
            memberService.join(member2);
            fail("예외가 발생해야 합니다");
        } catch(IllegalStateException e)
        {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }


 */
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}