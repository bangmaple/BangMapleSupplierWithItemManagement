/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.utils.validator.impl;

import bangnn.dtos.SuppliersDTO;
import bangnn.utils.validator.IValidator;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 *
 * @author bangmaple
 */
public class SuppliersValidatorImpl implements IValidator<SuppliersDTO> {

    private static String INVALID_SUPCODE;
    private static String INVALID_SUPNAME;
    private static String INVALID_ADDRESS;

    public static void loadInternationalizationResources(ResourceBundle resource) {
        INVALID_SUPCODE = resource.getString("INVALID_SUPCODE");
        INVALID_SUPNAME = resource.getString("INVALID_SUPNAME");
        INVALID_ADDRESS = resource.getString("INVALID_ADDRESS");
    }

    public boolean validateSupCode(final String supCode) throws IllegalArgumentException {
        if (!supCode.matches(Pattern.compile("^S[\\d]{4}$").pattern())) {
            throw new IllegalArgumentException(INVALID_SUPCODE);
        }
        return true;
    }

    public boolean validateSupName(final String supName) throws IllegalArgumentException {
        if (!supName.matches(Pattern.compile("^[\\w\\s+]{1,50}$").pattern())) {
            throw new IllegalArgumentException(INVALID_SUPNAME);
        }
        return true;
    }

    public boolean validateAddress(final String address) throws IllegalArgumentException {
        if (!address.matches(Pattern.compile("^[\\w\\s+]{1,50}$").pattern())) {
            throw new IllegalArgumentException(INVALID_ADDRESS);
        }
        return true;
    }

    @Override
    public boolean validate(SuppliersDTO dto) throws IllegalArgumentException {
        return validateSupCode(dto.getSupCode())
                && validateSupName(dto.getSupName())
                && validateAddress(dto.getAddress());
    }
}
