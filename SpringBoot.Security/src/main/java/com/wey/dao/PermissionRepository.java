package com.wey.dao;

import org.springframework.data.repository.CrudRepository;

import com.wey.pojo.auth.Permission;

public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
