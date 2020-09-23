/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.service.impl;

import bangnn.blos.UsersBLO;
import bangnn.dtos.UsersDTO;
import bangnn.repositories.impl.UsersCRUDRepositoryImpl;
import bangnn.utils.validator.impl.UsersValidatorImpl;
import java.util.Objects;
import bangnn.mapper.UserMapper;
import bangnn.service.UserService;
import java.util.ResourceBundle;

/**
 *
 * @author bangmaple
 */
public class UserServiceImpl implements UserService {

    private static String NOT_SUPPORTED;
    private static String INVALID_USER;
    private static String ACCOUNT_DISABLED;

    public static void loadInternationalizationResources(ResourceBundle resource) {
        NOT_SUPPORTED = resource.getString("NOT_SUPPORTED");
        INVALID_USER = resource.getString("INVALID_USER");
        ACCOUNT_DISABLED = resource.getString("ACCOUNT_DISABLED");
    }

    private final UsersCRUDRepositoryImpl repo = new UsersCRUDRepositoryImpl();
    private final UsersValidatorImpl validator = new UsersValidatorImpl();

    public UsersDTO checkLogin(final String username, final String password)
            throws IllegalArgumentException, NullPointerException {

        UsersDTO user = new UsersDTO(username, password);
        validator.validate(user);
        UsersDTO userInstance = getUserInstance(username);
        validateUserInstance(userInstance, user.getPassword());
        return user;
    }

    private boolean validateUserInstance(UsersDTO user, String typedPassword)
            throws IllegalArgumentException {
        if (!isActivatedUser(user)) {
            throw new IllegalArgumentException(ACCOUNT_DISABLED);
        }
        if (!isAuthenticated(user, typedPassword)) {
            throw new IllegalArgumentException(INVALID_USER);
        }
        return true;
    }

    private UsersDTO getUserInstance(final String username)
            throws NullPointerException {
        UsersBLO entity = Objects.requireNonNull(repo.get(username));
        return UserMapper.INSTANCE.userToUserDTO(entity);
    }

    private boolean isActivatedUser(final UsersDTO user) {
        return user.isActivated();
    }

    private boolean isAuthenticated(final UsersDTO user, final String password) {
        return user.getPassword().equals(password);
    }

    @Override
    public void createUser(UsersDTO user) throws IllegalArgumentException {
        throw new IllegalArgumentException(NOT_SUPPORTED);
    }

    @Override
    public UsersDTO getUser(String username) throws IllegalArgumentException {
        throw new IllegalArgumentException(NOT_SUPPORTED);
    }

    @Override
    public void updateUser(UsersDTO user, String username) throws IllegalArgumentException {
        throw new IllegalArgumentException(NOT_SUPPORTED);
    }

    @Override
    public void deleteUser(String username) throws IllegalArgumentException {
        throw new IllegalArgumentException(NOT_SUPPORTED);
    }
}
