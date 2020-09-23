/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.localization.impl;

import bangnn.controllers.LoginController;
import java.util.ResourceBundle;

/**
 *
 * @author bangmaple
 */
public class LoginControllerLocalizationImpl extends GenericLocalizationImpl {

    public LoginControllerLocalizationImpl() {
        super.init();
    }

    @Override
    public void loadLocalizationProperties() {
        ResourceBundle resource = ResourceBundle
                .getBundle("localization/LoginController", getLocale());
        LoginController.loadInternationalizationResources(resource);
    }

}
