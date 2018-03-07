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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "room")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r")
    , @NamedQuery(name = "Room.findRandomRooms", query = "SELECT r from Room r, Country c  inner join fetch c.roomList rc left join r.availabilityList al left join r.bookingList rb inner join fetch r.roomTypeList rt inner join fetch r.locationList rl inner join fetch r.photoList rp inner join fetch r.userList ul GROUP BY r.id ")
    , @NamedQuery(name = "Room.findById", query = "SELECT r FROM Room r WHERE r.id = :id")
    , @NamedQuery(name = "Room.findByOwnerId", query = "SELECT r from Room r inner join r.roomTypeList rt inner join r.locationList rl inner join fetch r.userList u where u.id = :id")
    , @NamedQuery(name = "Room.findNumberOfRoomsByOwnerId", query = "SELECT Count(r) from Room r inner join fetch r.userList u where u.id = :id")
    , @NamedQuery(name = "Room.findByWifi", query = "SELECT r FROM Room r WHERE r.wifi = :wifi")
    , @NamedQuery(name = "Room.findByRefridgerator", query = "SELECT r FROM Room r WHERE r.refridgerator = :refridgerator")
    , @NamedQuery(name = "Room.findByAircondition", query = "SELECT r FROM Room r WHERE r.aircondition = :aircondition")
    , @NamedQuery(name = "Room.findByHeating", query = "SELECT r FROM Room r WHERE r.heating = :heating")
    , @NamedQuery(name = "Room.findByKitchen", query = "SELECT r FROM Room r WHERE r.kitchen = :kitchen")
    , @NamedQuery(name = "Room.findByTv", query = "SELECT r FROM Room r WHERE r.tv = :tv")
    , @NamedQuery(name = "Room.findByParking", query = "SELECT r FROM Room r WHERE r.parking = :parking")
    , @NamedQuery(name = "Room.findByElevator", query = "SELECT r FROM Room r WHERE r.elevator = :elevator")
    , @NamedQuery(name = "Room.findByLivingRoom", query = "SELECT r FROM Room r WHERE r.livingRoom = :livingRoom")
    , @NamedQuery(name = "Room.findBySmoking", query = "SELECT r FROM Room r WHERE r.smoking = :smoking")
    , @NamedQuery(name = "Room.findByPets", query = "SELECT r FROM Room r WHERE r.pets = :pets")
    , @NamedQuery(name = "Room.findByEvents", query = "SELECT r FROM Room r WHERE r.events = :events")
    , @NamedQuery(name = "Room.findByMaxPeople", query = "SELECT r FROM Room r WHERE r.maxPeople = :maxPeople")
    , @NamedQuery(name = "Room.findByBedNumber", query = "SELECT r FROM Room r WHERE r.bedNumber = :bedNumber")
    , @NamedQuery(name = "Room.findByWcNumber", query = "SELECT r FROM Room r WHERE r.wcNumber = :wcNumber")
    , @NamedQuery(name = "Room.findByBedroomNumber", query = "SELECT r FROM Room r WHERE r.bedroomNumber = :bedroomNumber")
    , @NamedQuery(name = "Room.findByMinimumDays", query = "SELECT r FROM Room r WHERE r.minimumDays = :minimumDays")
    , @NamedQuery(name = "Room.findByCostPerPerson", query = "SELECT r FROM Room r WHERE r.costPerPerson = :costPerPerson")
    , @NamedQuery(name = "Room.findByCostPerDay", query = "SELECT r FROM Room r WHERE r.costPerDay = :costPerDay")
    , @NamedQuery(name = "Room.findByArea", query = "SELECT r FROM Room r WHERE r.area = :area")
    , @NamedQuery(name = "Room.findByFloor", query = "SELECT r FROM Room r WHERE r.floor = :floor")

    , @NamedQuery(name = "Room.findRoomWithAvailability", query = "Select r from Room r inner join fetch r.availabilityList where r.id = :id")
    , @NamedQuery(name = "Room.findEditAvailability", query = "Select r from Room r inner join fetch r.bookingList br where r.id = :id and ( :date <= br.dateFrom or :date <= br.dateTo)")
    , @NamedQuery(name = "Room.findRoomWithRoomType", query = "Select r from Room r inner join fetch r.roomTypeList where r.id = :id")
    , @NamedQuery(name = "Room.findRoomWithPhoto", query = "Select r from Room r inner join fetch r.photoList where r.id = :id")
    , @NamedQuery(name = "Room.findRoomWithLocation", query = "Select r from Room r inner join fetch r.locationList where r.id = :id")

    , @NamedQuery(name = "Room.findByAvailability", query = "SELECT r from Room r where r.id = :id and r.id in (select  r.id FROM Booking b join b.roomId r WHERE  ( b.dateFrom <= :start and  b.dateTo >= :end ) or (b.dateFrom >  :start and b.dateFrom < :end) or (b.dateTo > :start and b.dateTo < :end))")
    , @NamedQuery(name = "Room.basicSearch", query = "SELECT r from Room r, Country c  inner join fetch c.roomList rc left join r.availabilityList al left join r.bookingList rb inner join fetch r.roomTypeList rt inner join fetch r.locationList rl inner join fetch r.photoList rp inner join fetch r.userList ul where r.id not in (select  r.id FROM Booking b join b.roomId r WHERE ( b.dateFrom <= :start and  b.dateTo >= :end ) or (b.dateFrom >  :start and b.dateFrom < :end) or (b.dateTo > :start and b.dateTo < :end))  and( rc.id = r.id  and (rt.type = :type OR :type IS NULL ) and (rl.address = :address OR :address IS NULL )  and ( rl.city = :city OR :city IS NULL ) and ( c.name = :country OR :country IS NULL ) and ( al.dateFrom <= :start ) and (al.dateTo  >= :end) and (:start <= :end )  and ( rl.distrinct = :distrinct OR :distrinct IS NULL ) and ( r.maxPeople >= :pNumber OR :pNumber = 0) and ( r.costPerDay <= :cd OR :cd = 0 ) ) GROUP BY r.id ORDER BY r.costPerDay ")
    , @NamedQuery(name = "Room.advancedSearch", query = "SELECT r  from Room r, Country c inner join fetch c.roomList rc  left join r.availabilityList al left join r.bookingList rb inner join fetch r.roomTypeList rt inner join fetch r.locationList rl inner join fetch r.photoList rp  inner join fetch r.userList ul where r.id not in (select  r.id FROM Booking b join b.roomId r WHERE ( b.dateFrom <= :start and  b.dateTo >= :end ) or (b.dateFrom >  :start and b.dateFrom < :end) or (b.dateTo > :start and b.dateTo < :end)) and ( rc.id = r.id  and (rt.type = :type OR :type IS NULL )  and (rl.address = :address OR :address IS NULL ) and ( rl.city = :city OR :city IS NULL ) and ( c.name = :country OR :country IS NULL ) and ( al.dateFrom <= :start ) and (al.dateTo >= :end)  and (:start <= :end ) and ( rl.distrinct = :distrinct OR :distrinct IS NULL ) and ( r.maxPeople >= :pNumber OR :pNumber = 0) and ( r.costPerDay <= :costPday OR :costPday = 0 )and (r.wifi = :wf OR :wf IS NULL ) and (r.refridgerator = :rfr OR :rfr IS NULL ) and (r.kitchen = :ktc OR :ktc IS NULL ) and (r.elevator = :elv OR :elv IS NULL ) and (r.parking = :prk OR :prk IS NULL ) and (r.aircondition = :ac OR :ac IS NULL ) and (r.heating = :heat OR :heat IS NULL ) and (r.tv = :tivi OR :tivi IS NULL ) and (r.livingRoom = :li_room OR :li_room IS NULL ) and (r.smoking = :smok OR :smok IS NULL ) and (r.events = :event OR :event IS NULL ) and (r.pets = :pet OR :pet IS NULL ) and (r.floor = :flor OR :flor = 0) and (r.bedNumber = :bedNum OR :bedNum = 0) and (r.bedroomNumber = :bedroomNum OR :bedroomNum = 0) and (r.wcNumber = :wcNum OR :wcNum = 0) and (r.minimumDays <= :minDays OR :minDays = 0) and (r.area = :area OR :area = 0)) GROUP BY r.id ORDER BY r.costPerDay  ")
    , @NamedQuery(name = "Room.findAllRoomLocations", query = "SELECT rl from Room r inner join  r.locationList rl  where r.id = :id ")
    , @NamedQuery(name = "Room.findAllPhotographs", query = "SELECT rp from Room r inner join  r.photoList rp  where r.id = :id group by rp.id ")
    , @NamedQuery(name = "Room.findAllByRoomId", query = "SELECT DISTINCT r  from Room r, Country c join fetch c.roomList rc  left join r.availabilityList al inner join fetch r.roomTypeList rt inner join fetch r.locationList rl inner join fetch r.photoList rp  inner join fetch r.userList ul where r.id = :id")
    , @NamedQuery(name = "Room.findAllRoomTypes", query = "SELECT rt from Room r inner join r.roomTypeList rt where r.id = :id")})
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "wifi")
    private boolean wifi;
    @Basic(optional = false)
    @Column(name = "refridgerator")
    private boolean refridgerator;
    @Basic(optional = false)
    @Column(name = "aircondition")
    private boolean aircondition;
    @Basic(optional = false)
    @Column(name = "heating")
    private boolean heating;
    @Basic(optional = false)
    @Column(name = "kitchen")
    private boolean kitchen;
    @Basic(optional = false)
    @Column(name = "tv")
    private boolean tv;
    @Basic(optional = false)
    @Column(name = "parking")
    private boolean parking;
    @Basic(optional = false)
    @Column(name = "elevator")
    private boolean elevator;
    @Basic(optional = false)
    @Column(name = "living_room")
    private boolean livingRoom;
    @Basic(optional = false)
    @Column(name = "smoking")
    private boolean smoking;
    @Basic(optional = false)
    @Column(name = "pets")
    private boolean pets;
    @Basic(optional = false)
    @Column(name = "events")
    private boolean events;
    @Basic(optional = false)
    @Column(name = "max_people")
    private int maxPeople;
    @Basic(optional = false)
    @Column(name = "bed_number")
    private int bedNumber;
    @Basic(optional = false)
    @Column(name = "wc_number")
    private int wcNumber;
    @Basic(optional = false)
    @Column(name = "bedroom_number")
    private int bedroomNumber;
    @Basic(optional = false)
    @Column(name = "minimum_days")
    private int minimumDays;
    @Basic(optional = false)
    @Column(name = "cost_per_person")
    private double costPerPerson;
    @Basic(optional = false)
    @Column(name = "cost_per_day")
    private double costPerDay;
    @Basic(optional = false)
    @Column(name = "area")
    private double area;
    @Column(name = "floor")
    private Integer floor;
    @Lob
    @Column(name = "description")
    private String description;
    @JoinTable(name = "room_has_room_type", joinColumns = {
        @JoinColumn(name = "room_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "room_type_id", referencedColumnName = "id")})
    @ManyToMany
    private List<RoomType> roomTypeList;
    @JoinTable(name = "user_has_room", joinColumns = {
        @JoinColumn(name = "room_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany
    private List<User> userList;
    @ManyToMany(mappedBy = "roomList")
    private List<Photo> photoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private List<Booking> bookingList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private List<Location> locationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private List<Availability> availabilityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private List<Message> messageList;
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Country countryId;

    public Room() {
    }

    public Room(Integer id) {
        this.id = id;
    }

    public Room(Integer id, boolean wifi, boolean refridgerator, boolean aircondition, boolean heating, boolean kitchen, boolean tv, boolean parking, boolean elevator, boolean livingRoom, boolean smoking, boolean pets, boolean events, int maxPeople, int bedNumber, int wcNumber, int bedroomNumber, int minimumDays, double costPerPerson, double costPerDay, double area) {
        this.id = id;
        this.wifi = wifi;
        this.refridgerator = refridgerator;
        this.aircondition = aircondition;
        this.heating = heating;
        this.kitchen = kitchen;
        this.tv = tv;
        this.parking = parking;
        this.elevator = elevator;
        this.livingRoom = livingRoom;
        this.smoking = smoking;
        this.pets = pets;
        this.events = events;
        this.maxPeople = maxPeople;
        this.bedNumber = bedNumber;
        this.wcNumber = wcNumber;
        this.bedroomNumber = bedroomNumber;
        this.minimumDays = minimumDays;
        this.costPerPerson = costPerPerson;
        this.costPerDay = costPerDay;
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean getRefridgerator() {
        return refridgerator;
    }

    public void setRefridgerator(boolean refridgerator) {
        this.refridgerator = refridgerator;
    }

    public boolean getAircondition() {
        return aircondition;
    }

    public void setAircondition(boolean aircondition) {
        this.aircondition = aircondition;
    }

    public boolean getHeating() {
        return heating;
    }

    public void setHeating(boolean heating) {
        this.heating = heating;
    }

    public boolean getKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public boolean getTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean getParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean getElevator() {
        return elevator;
    }

    public void setElevator(boolean elevator) {
        this.elevator = elevator;
    }

    public boolean getLivingRoom() {
        return livingRoom;
    }

    public void setLivingRoom(boolean livingRoom) {
        this.livingRoom = livingRoom;
    }

    public boolean getSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean getPets() {
        return pets;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public boolean getEvents() {
        return events;
    }

    public void setEvents(boolean events) {
        this.events = events;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public int getWcNumber() {
        return wcNumber;
    }

    public void setWcNumber(int wcNumber) {
        this.wcNumber = wcNumber;
    }

    public int getBedroomNumber() {
        return bedroomNumber;
    }

    public void setBedroomNumber(int bedroomNumber) {
        this.bedroomNumber = bedroomNumber;
    }

    public int getMinimumDays() {
        return minimumDays;
    }

    public void setMinimumDays(int minimumDays) {
        this.minimumDays = minimumDays;
    }

    public double getCostPerPerson() {
        return costPerPerson;
    }

    public void setCostPerPerson(double costPerPerson) {
        this.costPerPerson = costPerPerson;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<RoomType> getRoomTypeList() {
        return roomTypeList;
    }

    public void setRoomTypeList(List<RoomType> roomTypeList) {
        this.roomTypeList = roomTypeList;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @XmlTransient
    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @XmlTransient
    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @XmlTransient
    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    @XmlTransient
    public List<Availability> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(List<Availability> availabilityList) {
        this.availabilityList = availabilityList;
    }

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public Country getCountryId() {
        return countryId;
    }

    public void setCountryId(Country countryId) {
        this.countryId = countryId;
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
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Room[ id=" + id + " ]";
    }

}
