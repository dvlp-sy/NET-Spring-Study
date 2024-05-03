package net.springstudy.service;

import net.springstudy.domain.Member;
import net.springstudy.repository.MemberRepository;
import net.springstudy.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService
{
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member)
    {
        validateDuplicatedMember(member); // 중복 회원 확인
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Member findOne(Long userId)
    {
        return memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("유저가 존재하지 않습니다"));
    }


}
