package model;

//import static entities.User_.nickname;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class BookingModel {

    private Integer id;
    private Date date_from;
    private Date date_to;
    private Integer owner_rating;
    private Integer room_rating;
    private UserModel visitor;

    public UserModel getVisitor() {
        return visitor;
    }

    public void setVisitor(UserModel visitor) {
        this.visitor = visitor;
    }

    public Integer getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return date_from;
    }

    @XmlElement
    public void setDateFrom(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDateTo() {
        return date_to;
    }

    @XmlElement
    public void setDateTo(Date date_to) {
        this.date_to = date_to;
    }

    public Integer getOwnerRating() {
        return owner_rating;
    }

    @XmlElement
    public void setOwnerRating(Integer owner_rating) {
        this.owner_rating = owner_rating;
    }

    public Integer getRoomRating() {
        return room_rating;
    }

    @XmlElement
    public void setRoomRating(Integer room_rating) {
        this.room_rating = room_rating;
    }
}
