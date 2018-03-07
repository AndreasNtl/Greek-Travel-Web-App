package model;

import entities.Country;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class RoomModel {

    private Integer id;
    private Integer max_people;
    private Integer bed_number;
    private Integer wc_number;
    private Integer bedroom_number;
    private Integer minimum_days;
    private Integer floor;
    private CountryModel country;
    private RoomTypeModel type;
    private Double cost_per_day;
    private Double cost_per_person;
    private Double area;
    private UserModel owner;
    private List<BookingModel> bookings;
    private Boolean wifi;
    private Boolean refridgerator;
    private Boolean aircondition;
    private Boolean heating;
    private Boolean kitchen;
    private Boolean tv;
    private Boolean parking;
    private Boolean elevator;
    private Boolean living_room;
    private Boolean smoking;
    private Boolean pets;
    private Boolean events;
    private String description;

    @XmlAttribute
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement
    public CountryModel getCountry() {
        return country;
    }

    public void setCountry(CountryModel country) {
        this.country = country;
    }

    @XmlElement
    public RoomTypeModel getType() {
        return type;
    }

    public void setType(RoomTypeModel type) {
        this.type = type;
    }

    @XmlAttribute
    public Double getCost_per_day() {
        return cost_per_day;
    }

    public void setCost_per_day(Double cost_per_day) {
        this.cost_per_day = cost_per_day;
    }

    @XmlElement
    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

    @XmlElement
    public List<BookingModel> getBooking() {
        return bookings;
    }

    public void setBooking(List<BookingModel> bookings) {
        this.bookings = bookings;
    }

    public Integer getMax_people() {
        return max_people;
    }

    @XmlAttribute
    public void setMax_people(Integer max_people) {
        this.max_people = max_people;
    }

    public Integer getBed_number() {
        return bed_number;
    }

    @XmlAttribute
    public void setBed_number(Integer bed_number) {
        this.bed_number = bed_number;
    }

    public Integer getWc_number() {
        return wc_number;
    }

    @XmlAttribute
    public void setWc_number(Integer wc_number) {
        this.wc_number = wc_number;
    }

    public Integer getBedroom_number() {
        return bedroom_number;
    }

    @XmlAttribute
    public void setBedroom_number(Integer bedroom_number) {
        this.bedroom_number = bedroom_number;
    }

    public Integer getMinimum_days() {
        return minimum_days;
    }

    @XmlAttribute
    public void setMinimum_days(Integer minimum_days) {
        this.minimum_days = minimum_days;
    }

    public Integer getFloor() {
        return floor;
    }

    @XmlAttribute
    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Double getCost_per_person() {
        return cost_per_person;
    }

    @XmlAttribute
    public void setCost_per_person(Double cost_per_person) {
        this.cost_per_person = cost_per_person;
    }

    public Double getArea() {
        return area;
    }

    @XmlAttribute
    public void setArea(Double area) {
        this.area = area;
    }

    public Boolean getWifi() {
        return wifi;
    }

    @XmlAttribute
    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getRefridgerator() {
        return refridgerator;
    }

    @XmlAttribute
    public void setRefridgerator(Boolean refridgerator) {
        this.refridgerator = refridgerator;
    }

    public Boolean getAircondition() {
        return aircondition;
    }

    @XmlAttribute
    public void setAircondition(Boolean aircondition) {
        this.aircondition = aircondition;
    }

    public Boolean getHeating() {
        return heating;
    }

    @XmlAttribute
    public void setHeating(Boolean heating) {
        this.heating = heating;
    }

    public Boolean getKitchen() {
        return kitchen;
    }

    @XmlAttribute
    public void setKitchen(Boolean kitchen) {
        this.kitchen = kitchen;
    }

    public Boolean getTv() {
        return tv;
    }

    @XmlAttribute
    public void setTv(Boolean tv) {
        this.tv = tv;
    }

    public Boolean getParking() {
        return parking;
    }

    @XmlAttribute
    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getElevator() {
        return elevator;
    }

    @XmlAttribute
    public void setElevator(Boolean elevator) {
        this.elevator = elevator;
    }

    public Boolean getLiving_room() {
        return living_room;
    }

    @XmlAttribute
    public void setLiving_room(Boolean living_room) {
        this.living_room = living_room;
    }

    public Boolean getSmoking() {
        return smoking;
    }

    @XmlAttribute
    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public Boolean getPets() {
        return pets;
    }

    @XmlAttribute
    public void setPets(Boolean pets) {
        this.pets = pets;
    }

    public Boolean getEvents() {
        return events;
    }

    @XmlAttribute
    public void setEvents(Boolean events) {
        this.events = events;
    }

    public String getDescription() {
        return description;
    }
    
    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    
}
