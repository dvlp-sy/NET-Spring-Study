package net.core.repository;

import net.core.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository
{
    void save(Member member);
    Optional<Member> findById(Long memberId);
}
