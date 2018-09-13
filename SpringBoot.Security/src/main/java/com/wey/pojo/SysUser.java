package com.wey.pojo;

import java.util.List;

public class SysUser {
    
    private String userName;
    private String password;
    private List<SysRole> roles;
    
    public SysUser(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<SysRole> getRoles() {
        return roles;
    }
    
    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
    
}
