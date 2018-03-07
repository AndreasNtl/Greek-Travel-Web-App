package dao;

import entities.Booking;
import java.util.Date;
import java.util.List;

public interface BookingDAO {

    public List<Booking> list();

    public void BookRoom(Date From, Date To, int renterId, int roomId);

    public List<Booking> ViewRentings(int id);

    public void RateRoom(Booking book);

    public Booking findBookingByid(int id, Date date);

    public List<Booking> findBookingByroomid(int id);

    public Booking findBookingDetailsByid(int rentingid);

    public Integer findAvgRating(int id);

    public Integer findReviews(int id);

    public List<Integer> findAvgRatingOfOwner(int id);

    public long findNumberOfBooking(int id);

    public List<Booking> pagination(int userId, int offset, int recordPerPage);
}
