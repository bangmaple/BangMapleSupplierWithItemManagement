/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.dtos;

import java.io.Serializable;

/**
 *
 * @author bangmaple
 */
public class ItemsDTO implements Serializable {

    private String itemCode;
    private String itemName;
    private String unit;
    private float price;
    private boolean supplying;
    private String supplier;

    public ItemsDTO() {
    }

    public ItemsDTO(String itemCode, String itemName, String unit, float price, boolean supplying, String supplier) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.unit = unit;
        this.price = price;
        this.supplying = supplying;
        this.supplier = supplier;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public boolean isSupplying() {
        return supplying;
    }

    public void setSupplying(boolean supplying) {
        this.supplying = supplying;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ItemsDTO{itemCode=").append(itemCode);
        sb.append(", itemName=").append(itemName);
        sb.append(", unit=").append(unit);
        sb.append(", price=").append(price);
        sb.append(", supplying=").append(supplying);
        sb.append(", supplier=").append(supplier);
        sb.append('}');
        return sb.toString();
    }

}
