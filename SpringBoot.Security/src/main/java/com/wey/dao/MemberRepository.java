package com.wey.dao;

import org.springframework.data.repository.CrudRepository;

import com.wey.pojo.auth.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {
    
    Member findByUsername(String username);
}
