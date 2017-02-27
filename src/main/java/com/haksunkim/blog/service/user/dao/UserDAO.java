package com.haksunkim.blog.service.user.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.haksunkim.blog.service.user.domain.User;

@Transactional
public interface UserDAO extends CrudRepository<User, Long>{

}
