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
public class SuppliersDTO implements Serializable {
    private String supCode;
    private String supName;
    private String address;
    private boolean colloborating;

    public SuppliersDTO() {
    }

    public SuppliersDTO(String supCode, String supName, String address, boolean colloborating) {
        this.supCode = supCode;
        this.supName = supName;
        this.address = address;
        this.colloborating = colloborating;
    }

    public String getSupCode() {
        return supCode;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isColloborating() {
        return colloborating;
    }

    public void setColloborating(boolean colloborating) {
        this.colloborating = colloborating;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SuppliersDTO{supCode=").append(supCode);
        sb.append(", supName=").append(supName);
        sb.append(", address=").append(address);
        sb.append(", colloborating=").append(colloborating);
        sb.append('}');
        return sb.toString();
    }
    
}
