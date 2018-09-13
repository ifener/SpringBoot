package com.wey.service;

import com.wey.pojo.auth.Member;

public interface MemberService {
    
    Member getMemberByName(String username);
}
