package com.wey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wey.dao.MemberRepository;
import com.wey.pojo.auth.Member;
import com.wey.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
    
    @Autowired
    MemberRepository memberRepository;
    
    @Override
    public Member getMemberByName(String username) {
        
        return memberRepository.findByUsername(username);
    }
    
}
