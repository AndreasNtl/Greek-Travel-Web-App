/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author psilos
 */
@Entity
@Table(name = "user_has_searched")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserHasSearched.findAll", query = "SELECT u FROM UserHasSearched u")
    , @NamedQuery(name = "UserHasSearched.findByPrimaryKey", query = "SELECT u FROM UserHasSearched u WHERE u.userHasSearchedPK.id = :uid and u.userHasSearchedPK.roomId = :rid ")
    , @NamedQuery(name = "UserHasSearched.findById", query = "SELECT u FROM UserHasSearched u WHERE u.userHasSearchedPK.id = :id")
    , @NamedQuery(name = "UserHasSearched.findByRoomId", query = "SELECT u FROM UserHasSearched u WHERE u.userHasSearchedPK.roomId = :roomId")})
public class UserHasSearched implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserHasSearchedPK userHasSearchedPK;

    public UserHasSearched() {
    }

    public UserHasSearched(UserHasSearchedPK userHasSearchedPK) {
        this.userHasSearchedPK = userHasSearchedPK;
    }

    public UserHasSearched(int id, int roomId) {
        this.userHasSearchedPK = new UserHasSearchedPK(id, roomId);
    }

    public UserHasSearchedPK getUserHasSearchedPK() {
        return userHasSearchedPK;
    }

    public void setUserHasSearchedPK(UserHasSearchedPK userHasSearchedPK) {
        this.userHasSearchedPK = userHasSearchedPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userHasSearchedPK != null ? userHasSearchedPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasSearched)) {
            return false;
        }
        UserHasSearched other = (UserHasSearched) object;
        if ((this.userHasSearchedPK == null && other.userHasSearchedPK != null) || (this.userHasSearchedPK != null && !this.userHasSearchedPK.equals(other.userHasSearchedPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserHasSearched[ userHasSearchedPK=" + userHasSearchedPK + " ]";
    }
    
}
