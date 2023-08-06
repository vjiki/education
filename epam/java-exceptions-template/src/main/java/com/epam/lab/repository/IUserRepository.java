package com.epam.lab.repository;

import com.epam.lab.entity.User;

public interface IUserRepository {

    User save(User user);

    User findByLogin(String login);

    void deleteByLogin(String login) throws UnsupportedOperationException;
}
