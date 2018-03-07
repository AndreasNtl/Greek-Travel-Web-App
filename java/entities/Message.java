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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m")
    , @NamedQuery(name = "Message.findById", query = "select m From Message m inner join fetch m.senderId inner join fetch m.receiverId inner join fetch m.roomId where m.id = :id")
    , @NamedQuery(name = "Message.findByTopic", query = "SELECT m FROM Message m WHERE m.topic = :topic")
    , @NamedQuery(name = "Message.findByText", query = "SELECT m FROM Message m WHERE m.text = :text")
    , @NamedQuery(name = "Message.findNumberOfIncommingMessage", query = "SELECT Count(m) FROM Message m inner join fetch m.senderId us inner join fetch m.receiverId ur where ur.id = :id and m.receiverDeletes = false")
    , @NamedQuery(name = "Message.findNumberOfOutgoingMessage", query = "SELECT Count(m) FROM Message m inner join fetch m.senderId us inner join fetch m.receiverId ur where us.id = :id and m.senderDeletes = false")
    , @NamedQuery(name = "Message.findBySubmittedDate", query = "SELECT m FROM Message m WHERE m.submittedDate = :submittedDate")
    , @NamedQuery(name = "Message.findBySenderDeletes", query = "SELECT m FROM Message m WHERE m.senderDeletes = :senderDeletes")
    , @NamedQuery(name = "Message.findByReceiverDeletes", query = "SELECT m FROM Message m WHERE m.receiverDeletes = :receiverDeletes")
    , @NamedQuery(name = "Message.findByroomId", query = "SELECT m FROM Message m inner join fetch m.roomId r inner join fetch m.senderId us inner join fetch m.receiverId ur WHERE r.id = :id and ur.id = :userId")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "topic")
    private String topic;
    @Basic(optional = false)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @Column(name = "submitted_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedDate;
    @Basic(optional = false)
    @Column(name = "senderDeletes")
    private boolean senderDeletes;
    @Basic(optional = false)
    @Column(name = "receiverDeletes")
    private boolean receiverDeletes;
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Room roomId;
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User senderId;
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User receiverId;

    public Message() {
    }

    public Message(Integer id) {
        this.id = id;
    }

    public Message(Integer id, String topic, String text, Date submittedDate, boolean senderDeletes, boolean receiverDeletes) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.submittedDate = submittedDate;
        this.senderDeletes = senderDeletes;
        this.receiverDeletes = receiverDeletes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public boolean getSenderDeletes() {
        return senderDeletes;
    }

    public void setSenderDeletes(boolean senderDeletes) {
        this.senderDeletes = senderDeletes;
    }

    public boolean getReceiverDeletes() {
        return receiverDeletes;
    }

    public void setReceiverDeletes(boolean receiverDeletes) {
        this.receiverDeletes = receiverDeletes;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public User getSenderId() {
        return senderId;
    }

    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }

    public User getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(User receiverId) {
        this.receiverId = receiverId;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Message[ id=" + id + " ]";
    }

}
