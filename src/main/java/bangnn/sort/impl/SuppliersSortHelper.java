/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.sort.impl;

import bangnn.sort.ISortable;
import java.util.Vector;

/**
 *
 * @author bangmaple
 */
public class SuppliersSortHelper implements ISortable<Vector, Integer> {

    private static final int SORT_BY_SUPPLIER_CODE = 0;
    private static final int SORT_BY_SUPPLIER_NAME = 1;
    private static final int SORT_BY_SUPPLIER_ADDRESS = 2;

    /**
     *
     * @param supplierData
     * @param index
     */
    @Override
    public void sortAscending(Vector supplierData, Integer index) {
        supplierData.sort((supplierDataRow1, supplierDataRow2) -> {
            Vector<String> dataRow1 = (Vector<String>) supplierDataRow1;
            Vector<String> dataRow2 = (Vector<String>) supplierDataRow2;
            switch (index) {
                case SORT_BY_SUPPLIER_CODE:
                    int supCodeDataRow2 = Integer
                            .parseInt(dataRow2.elementAt(SORT_BY_SUPPLIER_CODE)
                                    .substring(1));
                    int supCodeDataRow1 = Integer
                            .parseInt(dataRow1.elementAt(SORT_BY_SUPPLIER_CODE)
                                    .substring(1));
                    return Integer.compare(supCodeDataRow1, supCodeDataRow2);
                case SORT_BY_SUPPLIER_NAME:
                    String supNameDataRow2 = dataRow2.elementAt(SORT_BY_SUPPLIER_NAME);
                    String supNameDataRow1 = dataRow1.elementAt(SORT_BY_SUPPLIER_NAME);
                    return supNameDataRow1.compareToIgnoreCase(supNameDataRow2);
                case SORT_BY_SUPPLIER_ADDRESS:
                    String supAddressDataRow2 = dataRow2.elementAt(SORT_BY_SUPPLIER_ADDRESS);
                    String supAddressDataRow1 = dataRow1.elementAt(SORT_BY_SUPPLIER_ADDRESS);
                    return supAddressDataRow1.compareToIgnoreCase(supAddressDataRow2);
                default:
                    return 0;
            }
        });
    }

    /**
     *
     * @param supplierData
     * @param index
     */
    @Override
    public void sortDescending(Vector supplierData, Integer index) {
        supplierData.sort((supplierDataRow1, supplierDataRow2) -> {
            Vector<String> dataRow1 = (Vector<String>) supplierDataRow1;
            Vector<String> dataRow2 = (Vector<String>) supplierDataRow2;
            switch (index) {
                case 0:
                    int supCodeDataRow2 = Integer
                            .parseInt(dataRow2.elementAt(SORT_BY_SUPPLIER_CODE)
                                    .substring(1));
                    int supCodeDataRow1 = Integer
                            .parseInt(dataRow1.elementAt(SORT_BY_SUPPLIER_CODE)
                                    .substring(1));
                    return Integer.compare(supCodeDataRow2, supCodeDataRow1);
                case 1:
                    String supNameDataRow2 = dataRow2.elementAt(SORT_BY_SUPPLIER_NAME);
                    String supNameDataRow1 = dataRow1.elementAt(SORT_BY_SUPPLIER_NAME);
                    return supNameDataRow2.compareToIgnoreCase(supNameDataRow1);
                case 2:
                    String supAddressDataRow2 = dataRow2.elementAt(SORT_BY_SUPPLIER_ADDRESS);
                    String supAddressDataRow1 = dataRow1.elementAt(SORT_BY_SUPPLIER_ADDRESS);
                    return supAddressDataRow2.compareToIgnoreCase(supAddressDataRow1);
                default:
                    return 0;
            }
        });
    }

}
