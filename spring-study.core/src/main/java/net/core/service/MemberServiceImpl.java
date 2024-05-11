package net.core.service;

import net.core.domain.Member;
import net.core.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member)
    {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId)
    {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("Member Not Found"));
    }

    // Test 용도로 사용 (귀찮아서 여기 만듦)

    public MemberRepository getMemberRepository()
    {
        return memberRepository;
    }
}
