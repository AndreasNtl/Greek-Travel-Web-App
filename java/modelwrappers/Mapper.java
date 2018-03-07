/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelwrappers;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import entities.Booking;
import entities.Country;
import entities.Room;
import entities.RoomType;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import model.BookingModel;
import model.CountryModel;
import model.Export;
import model.RoomModel;
import model.RoomTypeModel;
import model.UserModel;

/**
 *
 * @author psilos
 */
public class Mapper {

    public void map(Export dataset, List<Room> room) {
        RoomWrapper roomWrapper = new RoomWrapper();
        List<RoomModel> roomModelList = new ArrayList<RoomModel>();

        for (Room entityRoom : room) {

            List<BookingModel> bookingModelList = new ArrayList<BookingModel>();
            RoomModel modelRoom = new RoomModel();
            //----      country     ----//
            Country country = entityRoom.getCountryId();
            CountryModel modelCountry = new CountryModel();
            modelCountry.setId(country.getId());
            modelCountry.setName(country.getName());
            modelRoom.setCountry(modelCountry);
            //----      roomtype    ----//
            RoomType type = entityRoom.getRoomTypeList().get(0);
            RoomTypeModel modelType = new RoomTypeModel();
            modelType.setId(type.getId());
            modelType.setType(type.getType());
            modelRoom.setType(modelType);
//            modelRoom = RoomMapper.map(entityRoom);
            modelRoom.setCost_per_day(entityRoom.getCostPerDay());
            modelRoom.setId(entityRoom.getId());
            modelRoom.setWifi(entityRoom.getWifi());
            modelRoom.setRefridgerator(entityRoom.getRefridgerator());
            modelRoom.setAircondition(entityRoom.getAircondition());
            modelRoom.setHeating(entityRoom.getHeating());
            modelRoom.setKitchen(entityRoom.getKitchen());
            modelRoom.setTv(entityRoom.getTv());
            modelRoom.setParking(entityRoom.getParking());
            modelRoom.setElevator(entityRoom.getElevator());
            modelRoom.setLiving_room(entityRoom.getLivingRoom());
            modelRoom.setSmoking(entityRoom.getSmoking());
            modelRoom.setPets(entityRoom.getPets());
            modelRoom.setEvents(entityRoom.getEvents());
            modelRoom.setMax_people(entityRoom.getMaxPeople());
            modelRoom.setBedroom_number(entityRoom.getBedroomNumber());
            modelRoom.setWc_number(entityRoom.getWcNumber());
            modelRoom.setBed_number(entityRoom.getBedNumber());
            modelRoom.setMinimum_days(entityRoom.getMinimumDays());
            modelRoom.setCost_per_person(entityRoom.getCostPerPerson());
            modelRoom.setArea(entityRoom.getArea());
            modelRoom.setFloor(entityRoom.getFloor());
            modelRoom.setDescription(entityRoom.getDescription());

            if (entityRoom.getUserList() != null && entityRoom.getUserList().size() > 0) {
                User entityOwner = entityRoom.getUserList().get(0);

                UserModel ownerModel = new UserModel();
                ownerModel.setId(entityOwner.getId());
                ownerModel.setNickname(entityOwner.getNickname());
                ownerModel.setPassword(entityOwner.getPassword());
                ownerModel.setName(entityOwner.getFirstName());
                ownerModel.setSurname(entityOwner.getSurname());
                ownerModel.setEmail(entityOwner.getEmail());
                ownerModel.setPhonenumber(entityOwner.getPhoneNumber());
                modelRoom.setOwner(ownerModel);
            }

            BookingDAO daoBooking = new BookingDAOImpl();
            List<Booking> roomBookings = daoBooking.findBookingByroomid(modelRoom.getId());
            if (!roomBookings.isEmpty()) {
                for (Booking entityBooking : roomBookings) {
                    BookingModel modelBooking = new BookingModel();
                    modelBooking.setId(entityBooking.getId());
                    modelBooking.setDateFrom(entityBooking.getDateFrom());
                    modelBooking.setDateTo(entityBooking.getDateTo());
                    modelBooking.setOwnerRating(entityBooking.getOwnerRating());
                    modelBooking.setRoomRating(entityBooking.getRoomRating());
                    UserModel visitorModel = new UserModel();
                    visitorModel.setNickname(entityBooking.getUserId().getNickname());
                    visitorModel.setId(entityBooking.getUserId().getId());
                    visitorModel.setName(entityBooking.getUserId().getFirstName());
                    visitorModel.setSurname(entityBooking.getUserId().getSurname());
                    visitorModel.setPhonenumber(entityBooking.getUserId().getPhoneNumber());
                    visitorModel.setEmail(entityBooking.getUserId().getEmail());
                    modelBooking.setVisitor(visitorModel);
                    bookingModelList.add(modelBooking);
                }
                modelRoom.setBooking(bookingModelList);
            }
            roomModelList.add(modelRoom);
        }

        roomWrapper.setRoom(roomModelList);

        dataset.setRooms(roomWrapper);
    }
}
