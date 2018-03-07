
package dao;

import entities.Availability;
import entities.Location;
import entities.Photo;
import entities.Room;
import entities.RoomType;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface RoomDAO {

    public List<Room> listRandomRooms();
    
    public List<Room> list();
    
    public List<Integer> list_ids();
    
    public void create(Room r, Availability a, Location l);

    public List<Room> findAllRoomsInACountry(int id);

    public List<Room> findRoomById(int id);
    
    public List<Room> findRecommendationsByUserId(int id);

    public List<Photo> listPhotographs(int id);

    public List<RoomType> listRoomTypes(int id);

    public List<Location> listRoomLocation(int id);

    public List<Room> padignationRoomList(Integer id, int offset, int recordPerPage);
    
    public List<Room> ownerRoomList(Integer id);
    
     public long findNumberOfOwnerRooms(int id);

    public List<Room> findOwnerRooms(int id);

    public Boolean checkRoomsAvailability(int roomId, Date from, Date to);

    public List<Room> ViewRentings(int id);

    public List<Room> findBookedOwnerRooms(int id) throws ParseException;

    public List<Room> findRoomSearchPagination(int offset, int recordPerPage, String type, String address, String country, String city, String distrinct, int pNumber,
            double cd, Date startDate, Date endDate);

    public List<Room> findRoomSearch( String type, String address, String country, String city, String distrinct, int pNumber,
            double cd, Date startDate, Date endDate);

    public List<Room> findAdvancedRoomSearch(String type, String address, String country, String city, String distrinct, int pNumber,
            double costPday, Date startDate, Date endDate, Boolean wf, Boolean rfr, Boolean ac, Boolean heat,
            Boolean ktc, Boolean tivi, Boolean prk, Boolean elv, Boolean li_room, Boolean smok, double area, Integer flor,
            int bedNum, int bedroomNum, Boolean pet, Boolean event, int wcNum, int minDays);
    
    public List<Room> findAdvancedRoomSearchPagination(int offset, int recordPerPage, String type, String address, String country, String city, String distrinct, int pNumber,
            double costPday, Date startDate, Date endDate, Boolean wf, Boolean rfr, Boolean ac, Boolean heat,
            Boolean ktc, Boolean tivi, Boolean prk, Boolean elv, Boolean li_room, Boolean smok, double area, Integer flor,
            int bedNum, int bedroomNum, Boolean pet, Boolean event, int wcNum, int minDays);

    public Room findAllCharacteristicsById(int id);

    public boolean updateCountry(Room oldRoom);

    public boolean updateDetails(Room oldRoom);

    public void updatePhoto(Room newRoom);

    public boolean updateLocation(Room newRoom);

    public boolean updateTypeRoom(Room newRoom);

    public boolean updateAvailability(Room newRoom);
    
    public boolean editAvailability(int id, Date date);
}
