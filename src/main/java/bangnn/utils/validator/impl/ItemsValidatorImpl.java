/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.utils.validator.impl;

import bangnn.dtos.ItemsDTO;
import bangnn.utils.validator.IValidator;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 *
 * @author bangmaple
 */
public class ItemsValidatorImpl implements IValidator<ItemsDTO> {

    private static String VALIDATE_ITEM_CODE_FAILED;
    private static String VALIDATE_ITEM_NAME_FAILED;
    private static String VALIDATE_ITEM_UNIT_FAILED;
    private static String VALIDATE_ITEM_PRICE_MAXIMUM_FAILED;
    private static String VALIDATE_ITEM_PRICE_MINIMUM_FAILED;

    public static void loadInternationalizationResources(ResourceBundle resource) {
        VALIDATE_ITEM_CODE_FAILED = resource.getString("VALIDATE_ITEM_CODE_FAILED");
        VALIDATE_ITEM_NAME_FAILED = resource.getString("VALIDATE_ITEM_NAME_FAILED");
        VALIDATE_ITEM_UNIT_FAILED = resource.getString("VALIDATE_ITEM_UNIT_FAILED");
        VALIDATE_ITEM_PRICE_MAXIMUM_FAILED = resource
                .getString("VALIDATE_ITEM_PRICE_MAXIMUM_FAILED");
        VALIDATE_ITEM_PRICE_MINIMUM_FAILED = resource
                .getString("VALIDATE_ITEM_PRICE_MINIMUM_FAILED");
        
    }
    
    
    public boolean validateItemCode(final String itemCode) throws IllegalArgumentException {
        if (!itemCode.matches(Pattern.compile("^I[\\d]{4}$").pattern())) {
            throw new IllegalArgumentException(VALIDATE_ITEM_CODE_FAILED);
        }
        return true;
    }

    public boolean validateItemName(final String itemName) throws IllegalArgumentException {
        boolean check = itemName.matches(Pattern.compile("^[\\w]{1,50}$").pattern());
        if (!check) {
            throw new IllegalArgumentException(VALIDATE_ITEM_NAME_FAILED);
        }
        return check;
    }

    public boolean validateItemUnit(final String itemUnit) throws IllegalArgumentException {
        if (!itemUnit.matches(Pattern.compile("^[\\w]{1,50}$").pattern())) {
            throw new IllegalArgumentException(VALIDATE_ITEM_UNIT_FAILED);
        }
        return true;
    }

    public boolean validateItemPrice(final Double itemPrice) throws IllegalArgumentException {
        if (itemPrice > 100_000_000) {
            throw new IllegalArgumentException(VALIDATE_ITEM_PRICE_MAXIMUM_FAILED);
        } else if (itemPrice < 0.0) {
            throw new IllegalArgumentException(VALIDATE_ITEM_PRICE_MINIMUM_FAILED);
        }
        return true;
    }

    @Override
    public boolean validate(ItemsDTO dto) throws IllegalArgumentException {
        return validateItemCode(dto.getItemCode())
                && validateItemName(dto.getItemName())
                && validateItemUnit(dto.getUnit())
                && validateItemPrice(dto.getPrice().doubleValue());
    }

}
