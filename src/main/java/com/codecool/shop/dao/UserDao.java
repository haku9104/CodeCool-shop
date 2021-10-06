package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.util.List;

public interface UserDao {
    void add(User player);
    void update(User player);
    User get(int id);
}
