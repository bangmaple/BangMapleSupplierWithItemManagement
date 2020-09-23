/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.localization.impl;

import bangnn.service.impl.ItemServiceImpl;
import java.util.ResourceBundle;

/**
 *
 * @author bangmaple
 */
public class ItemServiceLocalizationImpl extends GenericLocalizationImpl {

    public ItemServiceLocalizationImpl() {
        super.init();
    }

    @Override
    public void loadLocalizationProperties() {
        ResourceBundle resource = ResourceBundle
                .getBundle("localization/ItemServiceImpl", super.getLocale());
        ItemServiceImpl.loadInternationalizationResources(resource);
    }
    
}
