package com.wey.mybatis.mapper;

import java.util.List;

import com.wey.pojo.auth.Permission;

public interface PermissionMapper {
    
    public List<Permission> findAll();
    
    public List<Permission> findAllPermission();
    
    public List<Permission> findByAdminUserId(int userId);
}
