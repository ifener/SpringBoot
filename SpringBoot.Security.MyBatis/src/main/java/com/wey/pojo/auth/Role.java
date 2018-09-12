package com.wey.pojo.auth;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    
    private Long id;
    private String roleName;
    private List<Permission> permissions;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public List<Permission> getPermissions() {
        return permissions;
    }
    
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    
}
