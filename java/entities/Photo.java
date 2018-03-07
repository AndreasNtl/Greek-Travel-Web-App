package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "photo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Photo.findAll", query = "SELECT p FROM Photo p")
    , @NamedQuery(name = "Photo.findById", query = "SELECT p FROM Photo p WHERE p.id = :id")
    , @NamedQuery(name = "Photo.findByroomId", query = "SELECT p FROM Photo p inner join fetch p.roomList rl WHERE rl.id = :id")
    , @NamedQuery(name = "Photo.findNumerOfPhotosInRoom", query = "SELECT Count(p) FROM Photo p inner join fetch p.roomList rl WHERE rl.id = :id")
    , @NamedQuery(name = "Photo.findByPhotographUrl", query = "SELECT p FROM Photo p WHERE p.photographUrl = :photographUrl")})
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "photograph_url")
    private String photographUrl;
    @JoinTable(name = "user_has_photo", joinColumns = {
        @JoinColumn(name = "photo_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany
    private List<User> userList;
    @JoinTable(name = "room_has_photo", joinColumns = {
        @JoinColumn(name = "photo_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "room_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Room> roomList;

    public Photo() {
    }

    public Photo(Integer id) {
        this.id = id;
    }

    public Photo(Integer id, String photographUrl) {
        this.id = id;
        this.photographUrl = photographUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotographUrl() {
        return photographUrl;
    }

    public void setPhotographUrl(String photographUrl) {
        this.photographUrl = photographUrl;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @XmlTransient
    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
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
        if (!(object instanceof Photo)) {
            return false;
        }
        Photo other = (Photo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Photo[ id=" + id + " ]";
    }

}
