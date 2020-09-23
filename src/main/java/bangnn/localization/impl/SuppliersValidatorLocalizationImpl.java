/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.localization.impl;

import bangnn.utils.validator.impl.SuppliersValidatorImpl;
import java.util.ResourceBundle;

/**
 *
 * @author bangmaple
 */
public class SuppliersValidatorLocalizationImpl extends GenericLocalizationImpl {

    public SuppliersValidatorLocalizationImpl() {
       super.init();
    }

    @Override
    public void loadLocalizationProperties() {
        ResourceBundle resource = ResourceBundle
                .getBundle("localization/SuppliersValidatorImpl", getLocale());
        SuppliersValidatorImpl.loadInternationalizationResources(resource);
    }
    
}
