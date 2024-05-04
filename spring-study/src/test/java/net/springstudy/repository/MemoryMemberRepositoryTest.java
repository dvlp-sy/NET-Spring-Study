package net.springstudy.repository;

import net.springstudy.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemoryMemberRepositoryTest
{
    @Autowired MemberRepository memberRepository;

    @AfterEach
    public void afterEach()
    {
        memberRepository.clearStore();
    }

    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("spring");
        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();

        Assertions.assertEquals(member, result);
    }

    @Test
    public void findByName()
    {
        Member member = new Member();
        member.setName("spring");
        memberRepository.save(member);

        Member result = memberRepository.findByName("spring").get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("spring");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
