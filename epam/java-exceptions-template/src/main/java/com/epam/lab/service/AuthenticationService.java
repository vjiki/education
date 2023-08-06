package com.epam.lab.service;

import com.epam.lab.entity.User;
import com.epam.lab.exception.NotCorrectPasswordException;
import com.epam.lab.exception.UserNotFoundException;
import com.epam.lab.repository.IUserRepository;

public class AuthenticationService implements IAuthenticationService {

    private IUserRepository userRepository;

    public AuthenticationService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Необходимо доработать данный метод следующим функционлом:
     * <p>
     * 1) Необходимо проверять наличие авторизующегося пользователя в списке зарегистрированных пользователей.
     * Если пользователь не найден, необходимо выбрасывать проверяемое исключение с названием UserNotFoundException
     * и текстом ошибки "Пользователь с таким логином не найден".
     * <p>
     * 2) Необходимо проверять, что пароль, который ввел пользователь совпадает с тем, что хранится в базе.
     * Если пароли не совпадают, необходимо выбрасывать исключение с названием NotCorrectPasswordException и
     * текстом "Пароль введен неверно!"
     *
     * @param user - пользователь проходящий авторизацию
     */
    @Override
    public User login(User user) throws UserNotFoundException, NotCorrectPasswordException {
        // Находим пользователя в базе
        User foundUser = userRepository.findByLogin(user.getLogin());

        if (foundUser == null) {
            throw new UserNotFoundException();
        }

        if (!user.getPassword().equals(foundUser.getPassword())) {
            throw new NotCorrectPasswordException();
        }

        // Устанавливаем найденного пользователя, который прошел все проверки, как вошедшего в систему.
        CurrentUserManager.setCurrentLoggedInUser(foundUser);

        return foundUser;
    }

    /**
     * Данный метод очищает данные о текущем (активном) пользователе.
     */
    @Override
    public void logout() {
        CurrentUserManager.setCurrentLoggedInUser(null);
    }
}
