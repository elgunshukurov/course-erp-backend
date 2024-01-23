package com.elgunsh.courseerpbackend.service.user;

import com.elgunsh.courseerpbackend.model.mybatis.user.User;

public interface UserService {

    User getByEmail(String email);

    void insert(User user);

    boolean checkByEmail(String email);

}
