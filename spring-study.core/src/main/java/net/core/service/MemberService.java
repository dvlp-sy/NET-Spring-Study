package net.core.service;

import net.core.domain.Member;
import org.springframework.stereotype.Service;

public interface MemberService
{
    void join(Member member);
    Member findMember(Long memberId);
}
