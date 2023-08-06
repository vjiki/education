package com.epam.lab.service;

import com.epam.lab.entity.User;
import com.epam.lab.repository.UserRepository;

/**
 * Данный класс хранит информацию о текущем авторизованном пользователе (пользователе который вошел в систему).
 * Считаем, что в систему может войти только один пользователь.
 * <p>
 * Псле вызова метода {@link AuthenticationService#login(User)}, если все проверки прошли успешно,
 * пользователь устанавливается в данное поле.
 * <p>
 * Поскольку методы статические и поле статическое, его можно доставать в других классх, если понадобится проверить,
 * какое пользователь сейчас находится в системе.
 * <p>
 * Это необходимо например при выполнении метода {@link UserRepository#deleteByLogin(String)}
 * <p>
 * Если установить значение поля currentUser обратно в null, будем считать, что пользователь вышел из системы.
 */
public class CurrentUserManager {

    private static User currentUser;

    private CurrentUserManager() {}

    public static User getCurrentLoggedInUser() {
        return currentUser;
    }

    public static void setCurrentLoggedInUser(User currentUser) {
        CurrentUserManager.currentUser = currentUser;
    }
}
