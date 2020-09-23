/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.localization.impl;

import bangnn.controllers.SupplierController;
import java.util.ResourceBundle;

/**
 *
 * @author bangmaple
 */
public class SupplierControllerLocalizationImpl extends GenericLocalizationImpl {

    public SupplierControllerLocalizationImpl() {
        super.init();
    }

    @Override
    public void loadLocalizationProperties() {
        ResourceBundle resource = ResourceBundle
                .getBundle("localization/SupplierController", getLocale());
        SupplierController.loadInternationalizationResources(resource);
    }
    
}
