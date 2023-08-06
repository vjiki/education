package com.epam.lab.service;

import com.epam.lab.entity.User;
import com.epam.lab.exception.NotCorrectPasswordException;
import com.epam.lab.exception.UserNotFoundException;

public interface IAuthenticationService {
    User login(User user) throws UserNotFoundException, NotCorrectPasswordException;

    void logout();
}
