/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.localization.impl;

import bangnn.localization.ILocalizable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author bangmaple
 */
public abstract class GenericLocalizationImpl implements ILocalizable {

    private static final Logger log = LogManager.getLogger(GenericLocalizationImpl.class);
    private Locale currentLocale;
    private String language;
    private String country;
    public void init() {
        initLocalization();
    }

    public void setLocale(String language, String country) {
        this.language = language;
        this.country = country;
    }

    public Locale getLocale() {
        return currentLocale;
    }

    private void initLocalization() {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            loadLocalizationResources(fr, br);
            currentLocale = new Locale(language, country);
        } catch (IOException | NullPointerException e) {
            log.atFatal().log("Failed to load localization resources!!");
            System.exit(0);
        } finally {
            closeIOStream(fr, br);
        }
    }

    private void loadLocalizationResources(FileReader fr, BufferedReader br)
            throws IOException, NullPointerException {
        File f = new File("language.properties");
        if (!f.exists()) {
            FileWriter fw = new FileWriter("language.properties");
            fw.write("language = en-US");
            fw.close();
        }
        br = new BufferedReader(new FileReader("language.properties"));
        String lang = br.readLine();
        if (lang != null) {
            String[] localization = lang.split("=")[1].trim().split("-");
            language = localization[0];
            country = localization[1];
        }
    }

    private void closeIOStream(FileReader fr, BufferedReader br) {
        try {
            if (br != null) {
                br.close();
            }
            if (fr != null) {
                fr.close();
            }
        } catch (IOException e) {
            log.atFatal().log("Failed to load localization resources!!");
            System.exit(0);
        }
    }
}
