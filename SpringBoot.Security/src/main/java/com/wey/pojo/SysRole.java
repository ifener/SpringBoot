package com.wey.pojo;

import java.util.List;

public class SysRole {
    
    private String code;
    private String name;
    private List<SysPermission> permissions;
    
    public SysRole(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<SysPermission> getPermissions() {
        return permissions;
    }
    
    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }
    
}
