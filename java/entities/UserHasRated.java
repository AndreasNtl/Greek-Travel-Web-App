/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
 * @author psilos
 */
@Entity
@Table(name = "user_has_rated")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserHasRated.findAll", query = "SELECT u FROM UserHasRated u")
    , @NamedQuery(name = "UserHasRated.findById", query = "SELECT u FROM UserHasRated u WHERE u.id = :id")
    , @NamedQuery(name = "UserHasRated.findByHasRated", query = "SELECT u FROM UserHasRated u WHERE u.hasRated = :hasRated")})
public class UserHasRated implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "has_rated")
    private boolean hasRated;

    public UserHasRated() {
    }

    public UserHasRated(Integer id) {
        this.id = id;
    }

    public UserHasRated(Integer id, boolean hasRated) {
        this.id = id;
        this.hasRated = hasRated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getHasRated() {
        return hasRated;
    }

    public void setHasRated(boolean hasRated) {
        this.hasRated = hasRated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasRated)) {
            return false;
        }
        UserHasRated other = (UserHasRated) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserHasRated[ id=" + id + " ]";
    }
    
}
