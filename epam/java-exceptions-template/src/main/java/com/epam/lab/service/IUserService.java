package com.epam.lab.service;

import com.epam.lab.entity.User;
import com.epam.lab.exception.NotAccessException;
import com.epam.lab.exception.SimplePasswordException;
import com.epam.lab.exception.UserAlreadyRegisteredException;

public interface IUserService {

    User register(User user)  throws IllegalArgumentException, UserAlreadyRegisteredException, SimplePasswordException;

    void delete(String login) throws NotAccessException;
}
