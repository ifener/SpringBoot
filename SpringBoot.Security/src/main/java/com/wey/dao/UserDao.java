package com.wey.dao;

import java.util.Arrays;

import org.springframework.stereotype.Repository;

import com.wey.pojo.SysPermission;
import com.wey.pojo.SysRole;
import com.wey.pojo.SysUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserDao {
    
    private SysRole admin = new SysRole("admin", "管理员");
    private SysRole developer = new SysRole("developer", "开发人员");
    {
        SysPermission p1 = new SysPermission("UserIndex", "个人中心", "/user/index");
        SysPermission p2 = new SysPermission("BookList", "图书列表", "/book/list");
        SysPermission p3 = new SysPermission("BookAll", "添加图书", "/book/add");
        SysPermission p4 = new SysPermission("BookAll", "查看图书", "/book/view");
        
        admin.setPermissions(Arrays.asList(p1, p2, p3, p4));
        developer.setPermissions(Arrays.asList(p1, p2));
    }
    
    public SysUser selectByName(String username) {
        // log.info("从数据库中查询用户");
        if ("zhangsan".equals(username)) {
            SysUser sysUser = new SysUser("zhangsan", "$2a$10$EIfFrWGINQzP.tmtdLd2hurtowwsIEQaPFR9iffw2uSKCOutHnQEm");
            sysUser.setRoles(Arrays.asList(admin, developer));
            return sysUser;
        }
        else if ("lisi".equals(username)) {
            SysUser sysUser = new SysUser("lisi", "$2a$10$EIfFrWGINQzP.tmtdLd2hurtowwsIEQaPFR9iffw2uSKCOutHnQEm");
            sysUser.setRoles(Arrays.asList(developer));
            return sysUser;
        }
        return null;
    }
}
