/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author psilos
 */
@Embeddable
public class UserHasSearchedPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "room_id")
    private int roomId;

    public UserHasSearchedPK() {
    }

    public UserHasSearchedPK(int id, int roomId) {
        this.id = id;
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }   

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) roomId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserHasSearchedPK)) {
            return false;
        }
        UserHasSearchedPK other = (UserHasSearchedPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.roomId != other.roomId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserHasSearchedPK[ id=" + id + ", roomId=" + roomId + " ]";
    }
    
}
