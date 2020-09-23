/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.utils;

import bangnn.dtos.ItemsDTO;
import bangnn.dtos.SuppliersDTO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author bangmaple
 */
public class CSVExportHelper {

    private static final Logger log = LogManager.getLogger(CSVExportHelper.class);

    private static final String ERROR_EXPORTING_TO_CSV = "Error while exporting "
            + "to CSV Format. Please contact the administator for help.";
    private static final String ERROR_EXPORTING_TO_CSV_LOGGER = "Error at CSVExportHelper: ";
    private BufferedWriter bw;
    private FileWriter fw;

    private void closeIO() throws IOException {
        if (fw != null) {
            fw.close();
        }
        if (bw != null) {
            bw.close();
        }
    }

    public void writeSuppliersData(List<SuppliersDTO> supplierData) throws IOException {
        File f = new File("Suppliers-Exported-"
                + new SimpleDateFormat("dd-MM-yyyy--HH-mm-ss")
                        .format(new Date(System.currentTimeMillis())) + ".csv");
        try {
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            bw.write("Code,Name,Address,Colloborating");
            bw.newLine();
            for (SuppliersDTO supplier : supplierData) {
                bw.write(new StringBuilder(supplier.getSupCode()).append(",")
                        .append(supplier.getSupName()).append(",")
                        .append(supplier.getAddress()).append(",")
                        .append(supplier.isColloborating()).append(",").toString());
                bw.newLine();
            }
        } catch (IOException e) {
            log.atError().log(ERROR_EXPORTING_TO_CSV_LOGGER + e.getMessage());
            throw new IOException(ERROR_EXPORTING_TO_CSV);
        } finally {
            closeIO();
        }
    }

    public void writeItemsData(List<ItemsDTO> itemsData) throws IOException {
        File f = new File("Items-Exported-"
                + new SimpleDateFormat("dd-MM-yyyy--HH-mm-ss")
                        .format(new Date(System.currentTimeMillis())) + ".csv");
        try {
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            bw.write("Item Code,Item Name,Supplier,Unit,Price,Supplying");
            bw.newLine();
            for (ItemsDTO item : itemsData) {
                bw.write(new StringBuilder(item.getItemCode()).append(",")
                        .append(item.getItemName()).append(",")
                        .append(item.getSupplier()).append(",")
                        .append(item.getUnit()).append(",")
                        .append(item.getPrice()).append(",")
                        .append(item.isSupplying()).toString());
                bw.newLine();
            }
        } catch (IOException e) {
            log.atError().log(ERROR_EXPORTING_TO_CSV_LOGGER + e.getMessage());
            throw new IOException(ERROR_EXPORTING_TO_CSV);
        } finally {
            closeIO();
        }
    }

}
