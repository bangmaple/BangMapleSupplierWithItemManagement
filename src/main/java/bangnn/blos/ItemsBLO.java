/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.blos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bangmaple
 */
@Entity
@Table(name = "tblItems")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemsBLO.findAll", query = "SELECT i FROM ItemsBLO i"),
    @NamedQuery(name = "ItemsBLO.findByItemCode", query = "SELECT i FROM ItemsBLO i WHERE i.itemCode = :itemCode"),
    @NamedQuery(name = "ItemsBLO.findByItemName", query = "SELECT i FROM ItemsBLO i WHERE i.itemName = :itemName"),
    @NamedQuery(name = "ItemsBLO.findByUnit", query = "SELECT i FROM ItemsBLO i WHERE i.unit = :unit"),
    @NamedQuery(name = "ItemsBLO.findByPrice", query = "SELECT i FROM ItemsBLO i WHERE i.price = :price"),
    @NamedQuery(name = "ItemsBLO.findBySupplying", query = "SELECT i FROM ItemsBLO i WHERE i.supplying = :supplying")})
public class ItemsBLO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String itemCode;
    @Column(length = 50)
    private String itemName;
    @Column(length = 50)
    private String unit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 53)
    private Double price;
    private Boolean supplying;
    @JoinColumn(name = "supCode", referencedColumnName = "supCode")
    @ManyToOne(fetch = FetchType.EAGER)
    private SuppliersBLO supCode;

    public ItemsBLO() {
    }

    public ItemsBLO(String itemCode) {
        this.itemCode = itemCode;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getSupplying() {
        return supplying;
    }

    public void setSupplying(Boolean supplying) {
        this.supplying = supplying;
    }

    public SuppliersBLO getSupCode() {
        return supCode;
    }

    public void setSupCode(SuppliersBLO supCode) {
        this.supCode = supCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemCode != null ? itemCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemsBLO)) {
            return false;
        }
        ItemsBLO other = (ItemsBLO) object;
        if ((this.itemCode == null && other.itemCode != null) || (this.itemCode != null && !this.itemCode.equals(other.itemCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bangnn.pbos.ItemsPBO[ itemCode=" + itemCode + " ]";
    }
    
}
