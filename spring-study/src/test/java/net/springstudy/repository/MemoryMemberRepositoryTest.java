package net.springstudy.repository;

import net.springstudy.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest
{
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach()
    {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("spring");
        memoryMemberRepository.save(member);

        Member result = memoryMemberRepository.findById(member.getId()).get();

        Assertions.assertEquals(member, result);
    }

    @Test
    public void findByName()
    {
        Member member = new Member();
        member.setName("spring");
        memoryMemberRepository.save(member);

        Member result = memoryMemberRepository.findByName("spring").get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("spring");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring");
        memoryMemberRepository.save(member2);

        List<Member> result = memoryMemberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
