package com.exadel.booking.service;

import com.exadel.booking.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User findByUsername(String username);
    User findById(Long id);
    void  delete(Long id);
}
