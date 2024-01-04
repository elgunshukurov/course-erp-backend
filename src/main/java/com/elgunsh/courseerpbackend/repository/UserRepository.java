package com.elgunsh.courseerpbackend.repository;

import com.elgunsh.courseerpbackend.model.mybatis.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserRepository {

    void insert(User user);

    Optional<User> findByEmail(@Param("email") String email);

}
