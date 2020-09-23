/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.utils;

import bangnn.dtos.ItemsDTO;
import bangnn.dtos.SuppliersDTO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author bangmaple
 */
public class CSVImportHelper {

    private static final Logger log = LogManager.getLogger(CSVImportHelper.class);

    private static final String ERROR_IMPORTING_CSV = "Error while importing data from CSV file. Please validate your data first before inserting.";
    private static final String ERROR_IMPORTING_CSV_LOG = "Error at CSVImportHelper: ";
    private BufferedReader br;
    private FileReader fr;

    private void closeIO() throws IOException {
        if (fr != null) {
            fr.close();
        }
        if (br != null) {
            br.close();
        }
    }

    public List<SuppliersDTO> readSuppliersData(final String path) throws IOException {
        List<SuppliersDTO> list = null;
        try {

        } catch (Exception e) {
            log.atError().log(ERROR_IMPORTING_CSV_LOG + e.getMessage());
            throw new IOException(ERROR_IMPORTING_CSV);
        } finally {
            closeIO();
        }
        return list;
    }

    public List<ItemsDTO> readItemsData(final String path) throws IOException {
        List<ItemsDTO> list = null;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String read;
            list = new LinkedList<>();
            br.readLine();
            while ((read = br.readLine()) != null) {
                String[] itemData = read.split(",");
                ItemsDTO item = new ItemsDTO(itemData[0], itemData[1], itemData[2],
                        Float.parseFloat(itemData[3]),
                        itemData[4].equals("TRUE"), itemData[5]);
                list.add(item);
            }
        } catch (IOException | NumberFormatException e) {
            log.atError().log(ERROR_IMPORTING_CSV_LOG + e.getMessage());
            throw new IOException(ERROR_IMPORTING_CSV);
        } finally {
            closeIO();
        }
        return list;
    }
}
