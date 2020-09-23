/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.controllers;

import bangnn.localization.impl.ItemServiceLocalizationImpl;
import bangnn.localization.impl.ItemsValidatorLocalizationImpl;
import bangnn.localization.impl.LoginControllerLocalizationImpl;
import bangnn.localization.impl.SupplierControllerLocalizationImpl;
import bangnn.localization.impl.SupplierServiceLocalizationImpl;
import bangnn.localization.impl.SuppliersValidatorLocalizationImpl;
import bangnn.localization.impl.UserServiceLocalizationImpl;
import bangnn.localization.impl.UsersValidatorLocalizationImpl;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author bangmaple
 */
public class MainController {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(MainController.class);

    public static void main(String[] args) {
        init();
    }

    public static void init() {

        try {
            loadLocalizationProperties();
            LoginController.init();
        } catch (UnsupportedLookAndFeelException ex) {
            log.atFatal().log("ERROR at MainController: " + ex.getMessage());
        }
    }

    private static void loadLocalizationProperties() {
        new LoginControllerLocalizationImpl().loadLocalizationProperties();
        new SupplierControllerLocalizationImpl().loadLocalizationProperties();
        new ItemServiceLocalizationImpl().loadLocalizationProperties();
        new ItemsValidatorLocalizationImpl().loadLocalizationProperties();
        new SupplierServiceLocalizationImpl().loadLocalizationProperties();
        new SuppliersValidatorLocalizationImpl().loadLocalizationProperties();
        new UserServiceLocalizationImpl().loadLocalizationProperties();
        new UsersValidatorLocalizationImpl().loadLocalizationProperties();
    }
}
