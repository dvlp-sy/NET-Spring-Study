package net.core.repository;

import net.core.domain.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {

    public static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member)
    {
        store.put(member.getId(), member);
    }

    @Override
    public Optional<Member> findById(Long memberId)
    {
        return Optional.ofNullable(store.get(memberId));
    }
}
