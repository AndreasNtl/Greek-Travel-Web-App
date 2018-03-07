package dao;

import algorithmhelpers.RecommendationElement;
import entities.Availability;
import entities.Booking;
import entities.Country;
import entities.Location;
import entities.Photo;
import entities.Room;
import entities.RoomType;
import entities.User;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import jpautils.EntityManagerHelper;

public class RoomDAOImpl implements RoomDAO {

    @Override
    public List<Room> list() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Room.findAll");
        List<Room> rooms = query.getResultList();
        return rooms;
    }

    @Override
    public List<Room> listRandomRooms() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Room.findRandomRooms");
        List<Room> rooms = query.getResultList();
        
        long seed = System.nanoTime();
                
        Collections.shuffle(rooms, new Random(seed));
        
        return rooms;
    }

    @Override
    public List<Integer> list_ids() {
        List<Integer> ids = new ArrayList<>();

        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Room.findAll");
        List<Room> rooms = query.getResultList();

        for (Room r : rooms) {
            ids.add(r.getId());
        }

        return ids;
    }

    @Override
    public List<Room> ownerRoomList(Integer id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Room.findByOwnerId").setParameter("id", id);
        List<Room> rooms = query.getResultList();
        return rooms;
    }

    @Override
    public long findNumberOfOwnerRooms(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        long roomNumber = 0;
        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Room.findNumberOfRoomsByOwnerId").setParameter("id", id);
            roomNumber = (long) q.getSingleResult();
            entityTransaction.commit();

        } catch (NoResultException e) {
            roomNumber = 0;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
        return roomNumber;
    }

    @Override
    public List<Room> padignationRoomList(Integer id, int offset, int recordPerPage) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Room.findByOwnerId").setFirstResult(offset).setMaxResults(recordPerPage).setParameter("id", id);
        List<Room> rooms = query.getResultList();
        return rooms;
    }

    @Override
    public List<Photo> listPhotographs(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Room.findAllPhotographs").setParameter("id", id);
        List<Photo> roomPhotos = query.getResultList();
        EntityManagerHelper.closeEntityManager();
        return roomPhotos;
    }

    @Override
    public List<RoomType> listRoomTypes(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Room.findAllRoomTypes").setParameter("id", id);
        List<RoomType> roomType = query.getResultList();
        EntityManagerHelper.closeEntityManager();
        return roomType;
    }

    @Override
    public List<Location> listRoomLocation(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Room.findAllRoomLocations").setParameter("id", id);
        List<Location> Location = query.getResultList();
        EntityManagerHelper.closeEntityManager();
        return Location;
    }

    @Override
    public Room findAllCharacteristicsById(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Object singleResult;
        Room r = null;
        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Room.findAllByRoomId").setParameter("id", id);
            singleResult = q.getSingleResult();
            r = (Room) singleResult;
            entityTransaction.commit();
        } catch (NoResultException e) {
            r = null;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

        return r;
    }

    @Override
    public void create(Room r, Availability a, Location l) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction = null;
        //boolean roomCreate = false;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();

            // **************************************************
            //                  Type assignment
            // **************************************************
            List<RoomType> insertedType = new ArrayList<>();

            for (RoomType rt : r.getRoomTypeList()) {
                Query q = em.createNamedQuery("RoomType.findById").setParameter("id", rt.getId());
                RoomType singleResult = (RoomType) q.getSingleResult();
                singleResult.setRoomList(new ArrayList<>());
                insertedType.add(singleResult);
            }

            r.getRoomTypeList().clear();

            for (RoomType rt : insertedType) {
                r.getRoomTypeList().add(rt);
                rt.getRoomList().add(r);
            }

            em.persist(r);

            // **************************************************
            //                  Photo assignment
            // **************************************************
            for (Photo p : r.getPhotoList()) {
                List<Room> roomList = new ArrayList<>();
                roomList.add(r);
                p.setRoomList(roomList);

                em.persist(p);
            }

            //   **************************************************
            //             Availability assignment
            //   **************************************************
            a.setRoomId(r);
            em.persist(a);
            r.getAvailabilityList().add(a);

            // **************************************************
            //                  Location assignment
            // **************************************************
            l.setRoomId(r);
            em.persist(l);
            r.getLocationList().add(l);

            //  **************************************************
            //                User assignment
            //  **************************************************
            List<User> insertedUser = new ArrayList<>();

            for (User u : r.getUserList()) {
                Query q1 = em.createNamedQuery("User.findUsersRoom").setParameter("id", u.getId());
                User singleResult1 = (User) q1.getSingleResult();
                singleResult1.setRoomList(new ArrayList<>());
                insertedUser.add(singleResult1);
            }
            r.getUserList().clear();

            for (User u : insertedUser) {
                r.getUserList().add(u);
                u.getRoomList().add(r);
            }

            entityTransaction.commit();
            //    roomCreate = true;
        } catch (RuntimeException e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

        //  return roomCreate;
    }

    @Override
    public List<Room> findAllRoomsInACountry(int id) {
        return null;
    }

    @Override
    public List<Room> findRoomById(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<Room> singleResult = null;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Room.findById").setParameter("id", id);
            entityTransaction.commit();

            singleResult = (List<Room>) q.getResultList();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

        return singleResult;
    }

    public List<RoomType> findRoomByType(String type) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Object singleResult;
        List<RoomType> r;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Room.findByType").setParameter("type", type);
            entityTransaction.commit();
            singleResult = q.getSingleResult();
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
        EntityManagerHelper.closeEntityManager();
        r = (List<RoomType>) singleResult;
        return r;
    }

    @Override
    public Boolean checkRoomsAvailability(int roomId, Date from, Date to) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Room.findByAvailability").setParameter("id", roomId).setParameter("start", from).setParameter("end", to);

            List<Room> resultList = q.getResultList();
            entityTransaction.commit();
            if (resultList.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            return null;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

    }

    @Override
    public List<Room> ViewRentings(int id) {

        EntityManager em = EntityManagerHelper.getEntityManager();
        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Room.findByUserId").setParameter("id", id);
            entityTransaction.commit();
            List<Room> resultList = q.getResultList();
            return resultList;

        } catch (NoResultException e) {
            return null;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

    }

    @Override
    public List<Room> findRoomSearchPagination(int offset, int recordPerPage, String type, String address, String country, String city, String distrinct, int pNumber, double cd, Date startDate, Date endDate) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        try {

            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q;

            q = em.createNamedQuery("Room.basicSearch");
            q.setFirstResult(offset);
            q.setMaxResults(recordPerPage);
            q.setParameter("type", type);
            q.setParameter("pNumber", pNumber);
            q.setParameter("cd", cd);
            q.setParameter("country", country);
            q.setParameter("city", city);
            q.setParameter("distrinct", distrinct);
            q.setParameter("address", address);
            q.setParameter("start", startDate);
            q.setParameter("end", endDate);

            entityTransaction.commit();
            List<Room> resultList = q.getResultList();

            return resultList;

        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public List<Room> findRoomSearch(String type, String address, String country, String city, String distrinct, int pNumber, double cd, Date startDate, Date endDate) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        try {

            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q;

            q = em.createNamedQuery("Room.basicSearch");
            q.setParameter("type", type);
            q.setParameter("pNumber", pNumber);
            q.setParameter("cd", cd);
            q.setParameter("country", country);
            q.setParameter("city", city);
            q.setParameter("distrinct", distrinct);
            q.setParameter("address", address);
            q.setParameter("start", startDate);
            q.setParameter("end", endDate);

            List<Room> resultList = q.getResultList();
            entityTransaction.commit();
            return resultList;

        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public List<Room> findAdvancedRoomSearch(String type, String address, String country, String city, String distrinct, int pNumber,
            double costPday, Date startDate, Date endDate, Boolean wf, Boolean rfr, Boolean ac, Boolean heat,
            Boolean ktc, Boolean tivi, Boolean prk, Boolean elv, Boolean li_room, Boolean smok, double area, Integer flor,
            int bedNum, int bedroomNum, Boolean pet, Boolean event, int wcNum, int minDays) {

        EntityManager em = EntityManagerHelper.getEntityManager();

        try {

            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q;

            q = em.createNamedQuery("Room.advancedSearch")
                    .setParameter("type", type)
                    .setParameter("pNumber", pNumber)
                    .setParameter("costPday", costPday)
                    .setParameter("country", country)
                    .setParameter("city", city)
                    .setParameter("distrinct", distrinct)
                    .setParameter("address", address)
                    .setParameter("start", startDate)
                    .setParameter("end", endDate)
                    .setParameter("wf", wf)
                    .setParameter("rfr", rfr)
                    .setParameter("ktc", ktc)
                    .setParameter("elv", elv)
                    .setParameter("prk", prk)
                    .setParameter("ac", ac)
                    .setParameter("heat", heat)
                    .setParameter("tivi", tivi)
                    .setParameter("li_room", li_room)
                    .setParameter("smok", smok)
                    .setParameter("event", event)
                    .setParameter("pet", pet)
                    .setParameter("bedNum", bedNum)
                    .setParameter("bedroomNum", bedroomNum)
                    .setParameter("wcNum", wcNum)
                    .setParameter("flor", flor)
                    .setParameter("minDays", minDays)
                    .setParameter("area", area);

            entityTransaction.commit();
            List<Room> resultList = q.getResultList();

            return resultList;

        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public List<Room> findAdvancedRoomSearchPagination(int offset, int recordPerPage, String type, String address, String country, String city, String distrinct, int pNumber,
            double costPday, Date startDate, Date endDate, Boolean wf, Boolean rfr, Boolean ac, Boolean heat,
            Boolean ktc, Boolean tivi, Boolean prk, Boolean elv, Boolean li_room, Boolean smok, double area, Integer flor,
            int bedNum, int bedroomNum, Boolean pet, Boolean event, int wcNum, int minDays) {

        EntityManager em = EntityManagerHelper.getEntityManager();

        try {

            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q;

            q = em.createNamedQuery("Room.advancedSearch")
                    .setFirstResult(offset)
                    .setMaxResults(recordPerPage)
                    .setParameter("type", type)
                    .setParameter("pNumber", pNumber)
                    .setParameter("costPday", costPday)
                    .setParameter("country", country)
                    .setParameter("city", city)
                    .setParameter("distrinct", distrinct)
                    .setParameter("address", address)
                    .setParameter("start", startDate)
                    .setParameter("end", endDate)
                    .setParameter("wf", wf)
                    .setParameter("rfr", rfr)
                    .setParameter("ktc", ktc)
                    .setParameter("elv", elv)
                    .setParameter("prk", prk)
                    .setParameter("ac", ac)
                    .setParameter("heat", heat)
                    .setParameter("tivi", tivi)
                    .setParameter("li_room", li_room)
                    .setParameter("smok", smok)
                    .setParameter("event", event)
                    .setParameter("pet", pet)
                    .setParameter("bedNum", bedNum)
                    .setParameter("bedroomNum", bedroomNum)
                    .setParameter("wcNum", wcNum)
                    .setParameter("flor", flor)
                    .setParameter("minDays", minDays)
                    .setParameter("area", area);

            entityTransaction.commit();
            List<Room> resultList = q.getResultList();

            return resultList;

        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public List<Room> findOwnerRooms(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q;

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            q = em.createQuery("SELECT DISTINCT  r from Room r left join r.bookingList bl inner join FETCH r.roomTypeList rt inner join FETCH  r.locationList rl inner join FETCH"
                    + " r.photoList rp inner join fetch r.userList ul where r.id = :id  and (( :date NOT BETWEEN bl.dateFrom and bl.dateTo ) OR ( bl.dateFrom IS NULL OR bl.dateTo IS NULL))");
            q.setParameter("id", id);
            q.setParameter("date", date);

            entityTransaction.commit();
            List<Room> resultList = q.getResultList();

            return resultList;

        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public List<Room> findBookedOwnerRooms(int id) throws ParseException {
        EntityManager em = EntityManagerHelper.getEntityManager();

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q;

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();

            q = em.createQuery("SELECT DISTINCT  r  from Room r left join r.bookingList bl inner join r.roomTypeList rt inner join  r.locationList rl inner join "
                    + " r.photoList rp inner join fetch r.userList ul where r.id = :id and ( :date BETWEEN bl.dateFrom and bl.dateTo ) ");
            q.setParameter("id", id);
            q.setParameter("date", date);

            entityTransaction.commit();
            List<Room> resultList = q.getResultList();

            return resultList;

        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public boolean updateCountry(Room newRoom) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction = null;
        Room oldRoom = null;
        Country country;
        boolean update = false;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Country.findByName").setParameter("name", newRoom.getCountryId().getName());
            country = (Country) q.getSingleResult();

            Query q1 = em.createNamedQuery("Room.findAllByRoomId").setParameter("id", newRoom.getId());
            Room oldroom = (Room) q1.getSingleResult();
            oldroom.setCountryId(country);

            update = true;
            entityTransaction.commit();
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
        } catch (RuntimeException e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                EntityManagerHelper.closeEntityManager();
            }
        }
        return update;
    }

@Override
    public boolean updateDetails(Room oldRoom) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction = null;
        Room newRoom;
        boolean update = false;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();

            Query q = em.createNamedQuery("Room.findAllByRoomId").setParameter("id", oldRoom.getId());
            newRoom = (Room) q.getSingleResult();

            newRoom.setWifi(oldRoom.getWifi());
            newRoom.setParking(oldRoom.getParking());
            newRoom.setElevator(oldRoom.getElevator());
            newRoom.setKitchen(oldRoom.getKitchen());
            newRoom.setKitchen(oldRoom.getKitchen());
            newRoom.setAircondition(oldRoom.getAircondition());
            newRoom.setHeating(oldRoom.getHeating());
            newRoom.setTv(oldRoom.getTv());
            newRoom.setLivingRoom(oldRoom.getLivingRoom());
            newRoom.setSmoking(oldRoom.getSmoking());
            newRoom.setPets(oldRoom.getPets());
            newRoom.setEvents(oldRoom.getEvents());
            newRoom.setRefridgerator(oldRoom.getRefridgerator());

            newRoom.setBedNumber(oldRoom.getBedNumber());
            newRoom.setBedroomNumber(oldRoom.getBedroomNumber());
            newRoom.setWcNumber(oldRoom.getWcNumber());
            newRoom.setMinimumDays(oldRoom.getMinimumDays());
            newRoom.setMaxPeople(oldRoom.getMaxPeople());

            newRoom.setArea(oldRoom.getArea());
            newRoom.setCostPerDay(oldRoom.getCostPerDay());
            newRoom.setCostPerPerson(oldRoom.getCostPerPerson());

            newRoom.setFloor(oldRoom.getFloor());
            newRoom.setDescription(oldRoom.getDescription());

            entityTransaction.commit();
            update = true;
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
        } catch (RuntimeException e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                EntityManagerHelper.closeEntityManager();
            }
        }
        return update;
    }

    @Override
    public void updatePhoto(Room newRoom) {
        //to tropopimeno room = newRoom
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction = null;
        Room oldRoom = null;

        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Room.findRoomWithPhoto").setParameter("id", newRoom.getId());
            oldRoom = (Room) q.getSingleResult();//brisko to palio room

            if (!newRoom.getPhotoList().isEmpty() && newRoom.getPhotoList().get(0).getPhotographUrl() != null) {//eleno an einai keni i null to url
                //    Photo newphoto = newRoom.getPhotoList().get(0);
                //   Photo oldphoto = oldRoom.getPhotoList().get(0);

                Photo p = new Photo();
                List<Photo> photoList = new ArrayList<>();
                List<Room> roomList = new ArrayList<>();
                p = newRoom.getPhotoList().get(0);
                photoList.add(p);
                roomList.add(oldRoom);
                oldRoom.setPhotoList(photoList);
                p.setRoomList(roomList);
                em.persist(p);

                //   Query q2 = em.createNamedQuery("Photo.findById").setParameter("id", oldphoto.getId());//briskot tin palia foto 
                //  oldphoto = (Photo) q2.getSingleResult();
                //   oldphoto.setPhotographUrl(newphoto.getPhotographUrl()); //allazw to url me tin kenourgia photo
            }

            entityTransaction.commit();

        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
        } catch (RuntimeException e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                EntityManagerHelper.closeEntityManager();
            }
        }

    }

    @Override
    public boolean updateLocation(Room newRoom) {
        //to tropopimeno room = newRoom
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction = null;
        Room oldRoom;
        boolean update = false;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Room.findRoomWithLocation").setParameter("id", newRoom.getId());
            oldRoom = (Room) q.getSingleResult();//brisko to palio room

            if (!newRoom.getLocationList().isEmpty()) {
                Location newLocation = newRoom.getLocationList().get(0);
                Location oldLocation = oldRoom.getLocationList().get(0);

                Query q2 = em.createNamedQuery("Location.findById").setParameter("id", oldLocation.getId());//briskot tin palia foto 
                oldLocation = (Location) q2.getSingleResult();

                oldLocation.setAccessComments(newLocation.getAccessComments());
                oldLocation.setAddress(newLocation.getAddress());
                oldLocation.setCity(newLocation.getCity());
                oldLocation.setDistrinct(newLocation.getDistrinct());
                oldLocation.setLatitude(newLocation.getLatitude());
                oldLocation.setLongitude(newLocation.getLongitude());
                oldLocation.setPostcode(newLocation.getPostcode());
                oldLocation.setRoomId(oldRoom);
            }
            entityTransaction.commit();
            update = true;
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
        } catch (RuntimeException e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                EntityManagerHelper.closeEntityManager();
            }
        }
        return update;
    }

    @Override
    public boolean updateAvailability(Room newRoom) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction = null;
        Room oldRoom;
        boolean update = false;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Room.findRoomWithAvailability").setParameter("id", newRoom.getId());
            oldRoom = (Room) q.getSingleResult();//brisko to palio room

            if (!newRoom.getAvailabilityList().isEmpty()) {
                Availability newAvailability = newRoom.getAvailabilityList().get(0);
                Availability oldAvailability = oldRoom.getAvailabilityList().get(0);

                Query q2 = em.createNamedQuery("Availability.findById").setParameter("id", oldAvailability.getId());//briskot to id
                oldAvailability = (Availability) q2.getSingleResult();

                oldAvailability.setDateFrom(newAvailability.getDateFrom());
                oldAvailability.setDateTo(newAvailability.getDateTo());
                oldAvailability.setRoomId(oldRoom);

            }
            entityTransaction.commit();
            update = true;
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
        } catch (RuntimeException e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                EntityManagerHelper.closeEntityManager();
            }
        }
        return update;
    }

    @Override
    public boolean updateTypeRoom(Room newRoom) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction = null;
        Room oldRoom;
        boolean update = false;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Room.findRoomWithRoomType").setParameter("id", newRoom.getId());
            oldRoom = (Room) q.getSingleResult();
            if (!newRoom.getRoomTypeList().isEmpty() && newRoom.getRoomTypeList().get(0).getType() != null) {

                RoomType newRoomType = newRoom.getRoomTypeList().get(0);
                Query q3 = em.createNamedQuery("RoomType.findByType").setParameter("type", newRoomType.getType());
                newRoomType = (RoomType) q3.getSingleResult();//list tou kenourgiou ti
                oldRoom.getRoomTypeList().clear();
                List<RoomType> listRoomType = new ArrayList<>();
                listRoomType.add(newRoomType);
                oldRoom.setRoomTypeList(listRoomType);
                newRoomType.getRoomList().add(oldRoom);

            }
            entityTransaction.commit();
            update = true;
        } catch (NoResultException e) {
//            EntityManagerHelper.closeEntityManager();
        } catch (RuntimeException e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                EntityManagerHelper.closeEntityManager();
            }
        }
        return update;
    }

    @Override
    public boolean editAvailability(int id, Date date) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Room.findEditAvailability").setParameter("id", id).setParameter("date", date);

            List<Room> resultList = q.getResultList();

            entityTransaction.commit();
            return resultList.isEmpty();
        } catch (RuntimeException e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    private HashMap<Integer, HashMap<Integer, Float>> initializeByRating(List<Integer> user_ids, List<Integer> room_ids, List<Booking> bookings) {
        HashMap<Integer, HashMap<Integer, Float>> dataset = new HashMap<>();

        UserHasSearchedDAO uhsDAO = new UserHasSearchedDAOImpl();

        HashMap<Integer, Float> roomSumRating = new HashMap<>();
        HashMap<Integer, Integer> roomRatingFrequency = new HashMap<>();
        HashMap<Integer, Float> roomAvgRating = new HashMap<>();

        // initialize all average room ratings to 0
        for (Integer i : room_ids) {
            roomSumRating.put(i, 0.0f);
            roomRatingFrequency.put(i, 0);
            roomAvgRating.put(i, 0.0f);
        }

        // initialize non zero values
        for (Booking b : bookings) {
            Integer user_id = b.getUserId().getId();
            Integer room_id = b.getRoomId().getId();
            Integer rating = b.getRoomRating();

            if (rating != null) {
                if (dataset.containsKey(user_id) == false) {
                    dataset.put(user_id, new HashMap<Integer, Float>());
                }

                int temp = rating;
                float ftemp = temp;
                Float value = ftemp;

                dataset.get(user_id).put(room_id, value);

                if (b.getRoomRating() != null) {
                    Integer id = b.getRoomId().getId(); // room_id
                    Float previousRating = roomSumRating.get(id);
                    Float newRating = previousRating + b.getRoomRating();
                    roomSumRating.put(id, newRating);

                    Integer prevFrequency = roomRatingFrequency.get(id);
                    Integer newFrequency = prevFrequency + 1;
                    roomRatingFrequency.put(id, newFrequency);
                }
            }
        }

        // calculate average rating per room
        for (Integer i : room_ids) {
            Float sumRating = roomSumRating.get(i);
            Integer frequency = roomRatingFrequency.get(i);
            Float avgRating = sumRating / frequency;
            roomAvgRating.put(i, avgRating);
        }

        // fill blanks with NAN
        for (Integer user_id : user_ids) {
            if (dataset.containsKey(user_id) == false) {
                // ##### no booking, so initialize with searches
                dataset.put(user_id, new HashMap<Integer, Float>());

                List<Integer> searched = uhsDAO.findByUserId(user_id);
                for (Integer room_id : searched) {
                    Float avgRating = roomAvgRating.get(room_id);
                    dataset.get(user_id).put(room_id, avgRating);
                }
            }

            // at least one boooking, initialize with ratings
            int k = 0;
            for (Integer room_id : room_ids) {
                if (!dataset.get(user_id).containsKey(room_id)) {
                    dataset.get(user_id).put(room_id, Float.NaN);
                } else {
                    k++;
                }
            }

            if (k == 0) {
                // no rooms rated and no rooms searched, nothing to do
            }
        }

        return dataset;
    }

    private float cosine_similarity(List<Integer> room_ids, HashMap<Integer, Float> user1, HashMap<Integer, Float> user2) {
        float sum = 0;
        float norm1 = 0;
        float norm2 = 0;
        int dim = room_ids.size();

        for (Integer room_id : room_ids) {
            Float v1 = user1.get(room_id);
            Float v2 = user2.get(room_id);

            float product = v1 * v2;
            sum = sum + product;

            norm1 = norm1 + v1 * v1;
            norm2 = norm2 + v2 * v2;
        }
        norm1 = (float) Math.sqrt(norm1);
        norm2 = (float) Math.sqrt(norm2);

        if (norm1 == 0 || norm2 == 0) {
            return 0;
        } else {
            float sim = sum / (norm1 * norm2);
            return Math.abs(sim);
        }
    }

    public List<Room> getRandomRooms() {
        List<Room> recommendations = new ArrayList<>();
        RoomDAO roomDao = new RoomDAOImpl();

        for (Room r : roomDao.listRandomRooms()) {
            recommendations.add(r);
            if (recommendations.size() == 6) {
                break;
            }
        }

        return recommendations;
    }

    public List<Room> getAllCandidateRooms(List<Integer> candidate_room_ids) {
        List<Room> recommendations = new ArrayList<>();
        RoomDAO roomDao = new RoomDAOImpl();

        for (Integer room_id : candidate_room_ids) {
            Room r = roomDao.findRoomById(room_id).get(0);
            recommendations.add(r);
        }

        return recommendations;
    }

    @Override
    public List<Room> findRecommendationsByUserId(int target_id) {
        UserDAO userDao = new UserDAOImpl();
        BookingDAO bookingDao = new BookingDAOImpl();
        RoomDAO roomDao = new RoomDAOImpl();

        List<Room> recommendations = new ArrayList<>();

        List<Integer> user_ids = userDao.list_ids();
        List<Integer> room_ids = list_ids();
        List<Integer> candidate_room_ids = new ArrayList<>();
        List<Booking> bookings = bookingDao.list();

        HashMap<Integer, Float> ratings_per_user = new HashMap<>();     // average rating for each user

        // Step 1: initialize matrix with ratings or searches
        HashMap<Integer, HashMap<Integer, Float>> dataset = initializeByRating(user_ids, room_ids, bookings);
        HashMap<Integer, HashMap<Integer, Float>> user_x_user_similarities = new HashMap<>();

        // Step 2: find candidates, add them to candidate room ids
        for (Integer room_id : room_ids) {
            Float element = dataset.get(target_id).get(room_id);

            if (element.isNaN()) {
                candidate_room_ids.add(room_id);
            }
        }

        // check extreme conditions
        // Condition 1: If user has rated or visited all rooms, return random rooms ...
        if (candidate_room_ids.isEmpty()) {
            // return random rooms
            return getRandomRooms();
        }

        // Condition 2: If candidate rooms are less than top , then return all
        if (candidate_room_ids.size() <= 6) {
            return getAllCandidateRooms(candidate_room_ids);
        }

        // Step 3a: normalization, find for each user his average score and store it to ratings_per_user
        for (Integer user_id : user_ids) {
            float avg_rating = 0;
            int frequency = 0;

            for (Integer room_id : room_ids) {
                Float element = dataset.get(user_id).get(room_id);

                if (!element.isNaN()) {
                    avg_rating = avg_rating + element;
                    frequency++;
                }
            }

            if (frequency > 0) {
                avg_rating = avg_rating / frequency;
            }

            ratings_per_user.put(user_id, avg_rating);
        }

        // Step 3b: normalization, subtract avg score of each user from his ratings
        for (Integer user_id : user_ids) {
            float avg_rating = ratings_per_user.get(user_id);

            for (Integer room_id : room_ids) {
                Float element = dataset.get(user_id).get(room_id);

                if (element != null && !element.isNaN()) {
                    float temp = element.floatValue();
                    temp = temp - avg_rating;
                    dataset.get(user_id).put(room_id, temp);
                } else {
                    dataset.get(user_id).put(room_id, 0.0f);
                }
            }
        }

        // Step 4: calculate similarity target x u
        if (user_x_user_similarities.containsKey(target_id) == false) {
            user_x_user_similarities.put(target_id, new HashMap<Integer, Float>());
        }

        HashMap<Integer, Float> r1 = dataset.get(target_id);   // rates of target_id for each room
        for (Integer user_id : user_ids) {
            HashMap<Integer, Float> r2 = dataset.get(user_id);   // rates of u2 for each room

            float s = cosine_similarity(room_ids, r1, r2);

            user_x_user_similarities.get(target_id).put(user_id, s);
        }

        // Step 5a: calculate normalization factor (z)
        float z = 0;
        float denom_z = 0;
        int n = 0;
        for (Integer neighbour : user_ids) {
            if (neighbour != target_id) {
                denom_z += user_x_user_similarities.get(target_id).get(neighbour);
                n++;
            }
        }
        denom_z = Math.abs(denom_z);
        if (denom_z != 0) {
            z = 1.0f / denom_z;
        } else {
            return getRandomRooms();
        }

        // Step 5b: for each candidate room, calculate score prediction
        RecommendationElement[] recommendationTable = new RecommendationElement[candidate_room_ids.size()];

        int i = 0;
        for (Integer candidate_room : candidate_room_ids) {
            float rui = 0;

            for (Integer neighbour : user_ids) {
                if (neighbour != target_id) {
                    rui += (user_x_user_similarities.get(target_id).get(neighbour)) * (dataset.get(neighbour).get(candidate_room));
                }
            }

            RecommendationElement re = new RecommendationElement(candidate_room, rui);
            recommendationTable[i] = re;
            i++;
        }

        // recommendationTable order by ryu descending ...
        Arrays.sort(recommendationTable, 0, recommendationTable.length);

        int limit = Math.min(candidate_room_ids.size(), 6);

        for (i = 0; i < limit; i++) {
            List<Room> rlist = roomDao.findRoomById(recommendationTable[i].getRoom_id());
            Room r = rlist.get(0);
            recommendations.add(r);
        }

        return recommendations;
    }

}
