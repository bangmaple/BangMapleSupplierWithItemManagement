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
public class ItemsSortHelper implements ISortable<Vector, Integer> {

    private static final int SORT_BY_ITEM_CODE = 0;
    private static final int SORT_BY_ITEM_NAME = 1;
    private static final int SORT_BY_ITEM_SUPPLIER = 2;
    private static final int SORT_BY_ITEM_UNIT = 3;
    private static final int SORT_BY_ITEM_PRICE = 4;
    private static final int SORT_BY_ITEM_SUPPLYING = 5;

    @Override
    public void sortAscending(Vector itemsData, Integer index) {
        itemsData.sort((itemDataRow1, itemDataRow2) -> {
            Vector<String> dataRow1 = (Vector<String>) itemDataRow1;
            Vector<String> dataRow2 = (Vector<String>) itemDataRow2;
            switch (index) {
                case SORT_BY_ITEM_CODE:
                    int itemCodeRow1 = Integer
                            .parseInt(dataRow1.elementAt(SORT_BY_ITEM_CODE)
                                    .substring(1));
                    int itemCodeRow2 = Integer
                            .parseInt(dataRow2.elementAt(SORT_BY_ITEM_CODE)
                                    .substring(1));
                    return Integer.compare(itemCodeRow1, itemCodeRow2);
                case SORT_BY_ITEM_NAME:
                    String itemNameRow1 = dataRow1.elementAt(SORT_BY_ITEM_NAME);
                    String itemNameRow2 = dataRow2.elementAt(SORT_BY_ITEM_NAME);
                    return itemNameRow1.compareToIgnoreCase(itemNameRow2);
                case SORT_BY_ITEM_SUPPLIER:
                    String itemSupplierRow1 = dataRow1.elementAt(SORT_BY_ITEM_SUPPLIER);
                    String itemSupplierRow2 = dataRow2.elementAt(SORT_BY_ITEM_SUPPLIER);
                    return itemSupplierRow1.compareToIgnoreCase(itemSupplierRow2);
                case SORT_BY_ITEM_UNIT:
                    String itemUnitRow1 = dataRow1.elementAt(SORT_BY_ITEM_UNIT);
                    String itemUnitRow2 = dataRow2.elementAt(SORT_BY_ITEM_UNIT);
                    return itemUnitRow1.compareToIgnoreCase(itemUnitRow2);
                case SORT_BY_ITEM_PRICE:
                    String itemPriceRow1 = dataRow1.elementAt(SORT_BY_ITEM_PRICE);
                    String itemPriceRow2 = dataRow2.elementAt(SORT_BY_ITEM_PRICE);
                    return itemPriceRow1.compareToIgnoreCase(itemPriceRow2);
                case SORT_BY_ITEM_SUPPLYING:
                    boolean itemSupplyingRow1 = dataRow1.elementAt(SORT_BY_ITEM_SUPPLYING).equals("TRUE");
                    boolean itemSupplyingRow2 = dataRow2.elementAt(SORT_BY_ITEM_SUPPLYING).equals("TRUE");
                    return Boolean.compare(itemSupplyingRow1, itemSupplyingRow2);
                default:
                    return 0;
            }
        });
    }

    @Override
    public void sortDescending(Vector itemsData, Integer index) {
        itemsData.sort((itemDataRow1, itemDataRow2) -> {
            Vector<String> dataRow1 = (Vector<String>) itemDataRow1;
            Vector<String> dataRow2 = (Vector<String>) itemDataRow2;
            switch (index) {
                case SORT_BY_ITEM_CODE:
                    int itemCodeRow1 = Integer
                            .parseInt(dataRow1.elementAt(SORT_BY_ITEM_CODE)
                                    .substring(1));
                    int itemCodeRow2 = Integer
                            .parseInt(dataRow2.elementAt(SORT_BY_ITEM_CODE)
                                    .substring(1));
                    return Integer.compare(itemCodeRow2, itemCodeRow1);
                case SORT_BY_ITEM_NAME:
                    String itemNameRow1 = dataRow1.elementAt(SORT_BY_ITEM_NAME);
                    String itemNameRow2 = dataRow2.elementAt(SORT_BY_ITEM_NAME);
                    return itemNameRow2.compareToIgnoreCase(itemNameRow1);
                case SORT_BY_ITEM_SUPPLIER:
                    String itemSupplierRow1 = dataRow1.elementAt(SORT_BY_ITEM_SUPPLIER);
                    String itemSupplierRow2 = dataRow2.elementAt(SORT_BY_ITEM_SUPPLIER);
                    return itemSupplierRow2.compareToIgnoreCase(itemSupplierRow1);
                case SORT_BY_ITEM_UNIT:
                    String itemUnitRow1 = dataRow1.elementAt(SORT_BY_ITEM_UNIT);
                    String itemUnitRow2 = dataRow2.elementAt(SORT_BY_ITEM_UNIT);
                    return itemUnitRow2.compareToIgnoreCase(itemUnitRow1);
                case SORT_BY_ITEM_PRICE:
                    String itemPriceRow1 = dataRow1.elementAt(SORT_BY_ITEM_PRICE);
                    String itemPriceRow2 = dataRow2.elementAt(SORT_BY_ITEM_PRICE);
                    return itemPriceRow2.compareToIgnoreCase(itemPriceRow1);
                case SORT_BY_ITEM_SUPPLYING:
                    boolean itemSupplyingRow1 = dataRow1.elementAt(SORT_BY_ITEM_SUPPLYING).equals("TRUE");
                    boolean itemSupplyingRow2 = dataRow2.elementAt(SORT_BY_ITEM_SUPPLYING).equals("TRUE");
                    return Boolean.compare(itemSupplyingRow2, itemSupplyingRow1);
                default:
                    return 0;
            }
        });
    }

}
