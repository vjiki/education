package com.epam.lab.service;

import com.epam.lab.entity.User;
import com.epam.lab.exception.NotAccessException;
import com.epam.lab.exception.SimplePasswordException;
import com.epam.lab.exception.UserAlreadyRegisteredException;
import com.epam.lab.repository.IUserRepository;
import com.epam.lab.repository.UserRepository;

public class UserService implements IUserService {

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Необходимо доработать данный метод следующим функционлом:
     * <p>
     * 1) Необходимо проверять наличие заполнения всех полей сущности User. Если же поле с логином или паролем не
     * заполнено или заполнено пустой строкой. Необходимо выбрасывать существующее непроверяемое исключение
     * {@link IllegalArgumentException} с текстом "Ошибка в заполнении полей".
     * <p>
     * 2) Необходимо запрещать регистрацию пользователя, если другой пользователь с подобным логином уже
     * зарегистрирован. Необходимо в таком случае выбрасывать проверяемое исключение с названием UserAlreadyRegisteredException
     * и текстом - "Пользователь с логином 'login' уже зарегистрирован", где login - логин пользователя.
     * <p>
     * 3) Необходимо запрещать регистрацию пользователя, если он пытается установить пароль состоящий из цифр.
     * В случае, если это происходит (например пароль = "123432") необходимо выбрасывать
     * исключение с названием SimplePasswordException и текстом - "Пароль не соответствует требованиям безопасности"
     *
     * @param user - даныне регистрирующегося пользователя
     */
    @Override
    public User register(User user) throws IllegalArgumentException, UserAlreadyRegisteredException, SimplePasswordException {

        //
        // Здесь необходимо реализовать перечисленные выше проверки
        //

        if(user.getPassword().isEmpty() || user.getPassword().equals("")
                || user.getLogin().isEmpty() || user.getLogin().equals("")) {
            throw new IllegalArgumentException("Ошибка в заполнении полей");
        }

        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyRegisteredException(user.getLogin());
        }

        if(user.getPassword().matches("[0-9]+") && user.getPassword().length() > 0) {
            throw new SimplePasswordException();
        }

        // Если все проверки успешно пройдены, сохраняем пользователя в базу
        return userRepository.save(user);
    }

    /**
     * Необходимо доработать данный метод следующим функционлом:
     * <p>
     * Если мы попытаемся вызвать метод удаления {@link UserRepository#deleteByLogin(String)}
     * пользователем не админом (считаем, что админ имеет логин Admin), то будет выброшено исключение
     * {@link UnsupportedOperationException} из репозитория.
     * <p>
     * При этом, текст этого исключения выглядит очень непрезентабельно.
     * <p>
     * Поэтому, если мы не перехватим (через try-catch) это исключение, то оно уйдет пользователю с данным текстом.
     * Нам необходимо это предотвратить, перехватив данное исключение и выбросив в ответ на него свое,
     * но уже с понятным для пользователя сообщением.
     * <p>
     * Название вашего исключения должно быть следующее - NotAccessException и текст сообщения должен
     * быть "Недостаточно прав для выполнения операции"
     *
     * @param login
     */
    public void delete(String login) throws NotAccessException {

        try {
            userRepository.deleteByLogin(login);
        } catch (UnsupportedOperationException e) {
            throw new NotAccessException();
        }
    }

}
