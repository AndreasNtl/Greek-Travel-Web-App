package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author psilos
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u inner join fetch u.photoList")
    , @NamedQuery(name = "User.findUserById", query = "SELECT u FROM User u WHERE u.id = :id ") 
    , @NamedQuery(name = "User.findNumberOfUsers", query = "SELECT Count(u) FROM User u") 
    , @NamedQuery(name = "User.findUsersRoom", query = "SELECT u FROM User u inner join fetch u.photoList  WHERE u.id = :id ") 
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u inner join fetch u.photoList WHERE u.id = :id ")
    , @NamedQuery(name = "User.findByRoomId", query = "SELECT u FROM User u inner join fetch u.roomList rl inner join fetch u.photoList pl WHERE rl.id = :id")
    , @NamedQuery(name = "User.findByIdAndRoles", query = "SELECT u FROM User u inner join fetch u.photoList ph inner join fetch u.roleList rl WHERE u.id = :id ")
    , @NamedQuery(name = "User.findByNickname", query = "SELECT u FROM User u inner join fetch u.photoList WHERE u.nickname = :nickname  ")
    , @NamedQuery(name = "User.findByNicknameAndRoles", query = "SELECT u FROM User u inner join fetch u.roleList WHERE u.nickname = :nickname")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "User.findBySurname", query = "SELECT u FROM User u WHERE u.surname = :surname")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByPhoneNumber", query = "SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nickname")
    private String nickname;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic(optional = false)
    @Column(name = "active")
    private int active;
    @ManyToMany(mappedBy = "userList")
    private List<Role> roleList;
    @ManyToMany(mappedBy = "userList")
    private List<Photo> photoList;
    @ManyToMany(mappedBy = "userList")
    private List<Room> roomList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Booking> bookingList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderId")
    private List<Message> messageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiverId")
    private List<Message> messageList1;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String nickname, String password, String firstName, String surname, String email, String phoneNumber, int active) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @XmlTransient
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @XmlTransient
    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @XmlTransient
    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    @XmlTransient
    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @XmlTransient
    public List<Message> getMessageList1() {
        return messageList1;
    }

    public void setMessageList1(List<Message> messageList1) {
        this.messageList1 = messageList1;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nickname=" + nickname + ", password=" + password + ", firstName=" + firstName + ", surname=" + surname + ", email=" + email + ", phoneNumber=" + phoneNumber + ", active=" + active + ", roleList=" + roleList + ", photoList=" + photoList + ", roomList=" + roomList + ", bookingList=" + bookingList + ", messageList=" + messageList + ", messageList1=" + messageList1 + '}';
    }  
}
