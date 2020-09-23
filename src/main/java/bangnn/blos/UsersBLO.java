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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author bangmaple
 */
@Entity
@Table(name = "tblUsers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersBLO.findAll", query = "SELECT u FROM UsersBLO u"),
    @NamedQuery(name = "UsersBLO.findByUserId", query = "SELECT u FROM UsersBLO u WHERE u.userId = :userId"),
    @NamedQuery(name = "UsersBLO.findByFullName", query = "SELECT u FROM UsersBLO u WHERE u.fullName = :fullName"),
    @NamedQuery(name = "UsersBLO.findByPassword", query = "SELECT u FROM UsersBLO u WHERE u.password = :password"),
    @NamedQuery(name = "UsersBLO.findByStatus", query = "SELECT u FROM UsersBLO u WHERE u.status = :status")})
public class UsersBLO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String userId;
    @Column(length = 50)
    private String fullName;
    @Column(length = 50)
    private String password;
    private Boolean status;

    public UsersBLO() {
    }

    public UsersBLO(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersBLO)) {
            return false;
        }
        UsersBLO other = (UsersBLO) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bangnn.pbos.UsersPBO[ userId=" + userId + " ]";
    }
    
}
