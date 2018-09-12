package com.wey.mybatis.mapper;

import com.wey.pojo.auth.Member;

public interface MemberMapper {
    
    public Member findByUserName(String username);
}
