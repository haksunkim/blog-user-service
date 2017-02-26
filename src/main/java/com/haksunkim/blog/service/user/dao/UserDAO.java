package com.haksunkim.blog.service.user.dao;

import org.springframework.data.repository.CrudRepository;

import com.haksunkim.blog.service.user.domain.User;

public interface UserDAO extends CrudRepository<User, Long>{

}
