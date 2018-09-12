package com.wey.interceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wey.mybatis.mapper.PermissionMapper;
import com.wey.pojo.auth.Permission;
import com.wey.pojo.auth.Role;

@Service
@Transactional
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
    
    @Autowired
    private PermissionMapper permissionMapper;
    
    private static volatile HashMap<String, Collection<ConfigAttribute>> maps = null;
    
    /**
     * 加载权限
     * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。
     * PostConstruct在构造函数之后执行,init()方法之前执行
     */
    @PostConstruct
    public void loadResourceDefine() {
        maps = new HashMap<>(16);
        Collection<ConfigAttribute> configAttributes;
        ConfigAttribute cfg;
        Iterable<Permission> list = permissionMapper.findAllPermission();
        for (Iterator<Permission> it = list.iterator(); it.hasNext();) {
            configAttributes = new ArrayList<ConfigAttribute>();
            Permission p = it.next();
            List<Role> roles = p.getRoles();
            if (roles != null && roles.size() > 0) {
                for (Role r : roles) {
                    cfg = new SecurityConfig(r.getRoleName());
                    configAttributes.add(cfg);
                }
            }
            
            // 用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            maps.put(p.getUrl(), configAttributes);
        }
    }
    
    /**
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，
     * 用来判定用户是否有此权限。如果不在权限表中则放行。
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (maps == null) {
            loadResourceDefine();
        }
        
        // object 是一个URL，被用户请求的url。
        FilterInvocation filterInvocation = (FilterInvocation) object;
        HttpServletRequest httpRequest = filterInvocation.getHttpRequest();
        Iterator<String> iterator = maps.keySet().iterator();
        while (iterator.hasNext()) {
            String url = iterator.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(url);
            if (requestMatcher.matches(httpRequest)) {
                return maps.get(url);
            }
        }
        
        return null;
    }
    
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
    
}
