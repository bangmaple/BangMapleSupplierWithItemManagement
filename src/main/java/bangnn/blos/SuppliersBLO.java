/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.blos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bangmaple
 */
@Entity
@Table(name = "tblSuppliers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SuppliersBLO.findAll", query = "SELECT s FROM SuppliersBLO s"),
    @NamedQuery(name = "SuppliersBLO.findBySupCode", query = "SELECT s FROM SuppliersBLO s WHERE s.supCode = :supCode"),
    @NamedQuery(name = "SuppliersBLO.findBySupName", query = "SELECT s FROM SuppliersBLO s WHERE s.supName = :supName"),
    @NamedQuery(name = "SuppliersBLO.findByAddress", query = "SELECT s FROM SuppliersBLO s WHERE s.address = :address"),
    @NamedQuery(name = "SuppliersBLO.findByCollaborating", query = "SELECT s FROM SuppliersBLO s WHERE s.collaborating = :collaborating")})
public class SuppliersBLO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String supCode;
    
    @Column(length = 50)
    private String supName;
    
    @Column(length = 50)
    private String address;
    
    private Boolean collaborating;
    
    @OneToMany(mappedBy = "supCode", fetch = FetchType.EAGER)
    private List<ItemsBLO> itemsPBOList;

    public SuppliersBLO() {
    }

    public SuppliersBLO(String supCode) {
        this.supCode = supCode;
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

    public Boolean getCollaborating() {
        return collaborating;
    }

    public void setCollaborating(Boolean collaborating) {
        this.collaborating = collaborating;
    }

    @XmlTransient
    public List<ItemsBLO> getItemsPBOList() {
        return itemsPBOList;
    }

    public void setItemsPBOList(List<ItemsBLO> itemsPBOList) {
        this.itemsPBOList = itemsPBOList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supCode != null ? supCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuppliersBLO)) {
            return false;
        }
        SuppliersBLO other = (SuppliersBLO) object;
        if ((this.supCode == null && other.supCode != null) || (this.supCode != null && !this.supCode.equals(other.supCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bangnn.pbos.SuppliersPBO[ supCode=" + supCode + " ]";
    }
    
}
