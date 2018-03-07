package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "booking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b")
    , @NamedQuery(name = "Booking.findById", query = "SELECT b FROM Booking b WHERE b.id = :id")
    , @NamedQuery(name = "Booking.findByIdforDetails", query = "SELECT b FROM Booking b inner join fetch b.roomId r inner join fetch r.photoList p inner join fetch b.userId u WHERE b.id = :id")
    , @NamedQuery(name = "Booking.findAvgRate", query = "SELECT avg(b.roomRating) FROM Booking b inner join fetch b.roomId r  WHERE r.id = :id and b.roomRating IS NOT NULL ")
    , @NamedQuery(name = "Booking.findReviews", query = "SELECT count(b.roomRating) FROM Booking b inner join fetch b.roomId r  WHERE r.id = :id and b.roomRating IS NOT NULL ")
    , @NamedQuery(name = "Booking.findAvgRateOfOwner", query = "SELECT avg(b.ownerRating) as average,count(b.ownerRating) as reviews FROM Booking b inner join b.roomId r WHERE r.id in (SELECT  r.id  from Room r inner join r.userList ul where ul.id = :id )")
    , @NamedQuery(name = "Booking.findByIdandDate", query = "SELECT b FROM Booking b WHERE b.id = :id and b.dateFrom <= :date")
    , @NamedQuery(name = "Booking.findByroomId", query = "SELECT b FROM Booking b inner join fetch b.roomId r inner join fetch b.userId u WHERE r.id = :id")
    , @NamedQuery(name = "Booking.findByDateFrom", query = "SELECT b FROM Booking b WHERE b.dateFrom = :dateFrom")
    , @NamedQuery(name = "Booking.findByDateTo", query = "SELECT b FROM Booking b WHERE b.dateTo = :dateTo")
    , @NamedQuery(name = "Booking.findByOwnerRating", query = "SELECT b FROM Booking b WHERE b.ownerRating = :ownerRating")
    , @NamedQuery(name = "Booking.findByRoomRating", query = "SELECT b FROM Booking b WHERE b.roomRating = :roomRating")
    , @NamedQuery(name = "Booking.findByUserIdNumberOfBooking", query = "select count(b) from Booking b inner join fetch b.roomId r inner join fetch b.userId u inner join fetch r.locationList l inner join fetch r.photoList p where u.id = :id")
    , @NamedQuery(name = "Booking.findByUserIdNumberOfBooking2", query = "select count(b) from Booking b where b.userId.id = :id")
    , @NamedQuery(name = "Booking.findByUserId", query = "select b from Booking b inner join fetch b.roomId r inner join fetch b.userId u inner join fetch r.locationList l inner join fetch r.photoList p where u.id = :id")

})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date_from")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @Basic(optional = false)
    @Column(name = "date_to")
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    @Column(name = "owner_rating")
    private Integer ownerRating;
    @Column(name = "room_rating")
    private Integer roomRating;
    @Lob
    @Column(name = "owner_comment")
    private String ownerComment;
    @Lob
    @Column(name = "room_comment")
    private String roomComment;
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Room roomId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Booking() {
    }

    public Booking(Integer id) {
        this.id = id;
    }

    public Booking(Integer id, Date dateFrom, Date dateTo) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getOwnerRating() {
        return ownerRating;
    }

    public void setOwnerRating(Integer ownerRating) {
        this.ownerRating = ownerRating;
    }

    public Integer getRoomRating() {
        return roomRating;
    }

    public void setRoomRating(Integer roomRating) {
        this.roomRating = roomRating;
    }

    public String getOwnerComment() {
        return ownerComment;
    }

    public void setOwnerComment(String ownerComment) {
        this.ownerComment = ownerComment;
    }

    public String getRoomComment() {
        return roomComment;
    }

    public void setRoomComment(String roomComment) {
        this.roomComment = roomComment;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Booking[ id=" + id + " ]";
    }

}
