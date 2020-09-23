/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.localization.impl;

import bangnn.service.impl.SupplierServiceImpl;
import java.util.ResourceBundle;

/**
 *
 * @author bangmaple
 */
public class SupplierServiceLocalizationImpl extends GenericLocalizationImpl {

    public SupplierServiceLocalizationImpl() {
        super.init();
    }

    @Override
    public void loadLocalizationProperties() {
        ResourceBundle resource = ResourceBundle
                .getBundle("localization/SupplierServiceImpl", super.getLocale());
        SupplierServiceImpl.loadInternationalizationResources(resource);
    }
    
}
