/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.utils.validator.impl;

import bangnn.dtos.UsersDTO;
import bangnn.utils.validator.IValidator;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 *
 * @author bangmaple
 */
public class UsersValidatorImpl implements IValidator<UsersDTO> {

    private static String INVALID_USER;
    
    public static void loadInternationalizationResources(ResourceBundle resource) {
        INVALID_USER = resource.getString("INVALID_USER");
    }

    private boolean validateUsername(final String username) throws IllegalArgumentException {
        if (!username.matches(Pattern.compile("^[\\w]{1,10}$").pattern())) {
            throw new IllegalArgumentException(INVALID_USER);
        }
       
        return true;
    }

    private boolean validatePassword(final String password) throws IllegalArgumentException {
        if (!password.matches(Pattern.compile("^[\\w]{1,50}$").pattern())) {
            throw new IllegalArgumentException(INVALID_USER);
        }
        return true;
    }

    @Override
    public boolean validate(UsersDTO dto) throws IllegalArgumentException {
        return validateUsername(dto.getUsername())
                && validatePassword(dto.getPassword());
    }

}
