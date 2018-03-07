package dao;

import entities.Booking;
import entities.Room;
import entities.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;

public class BookingDAOImpl implements BookingDAO {

    @Override
    public List<Booking> list() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Booking.findAll");
        List<Booking> bookings = query.getResultList();
        return bookings;
    }

    @Override
    public List<Booking> pagination(int userId, int offset, int recordPerPage) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Booking.findByUserId").setFirstResult(offset).setMaxResults(recordPerPage).setParameter("id", userId);
        List<Booking> booking = query.getResultList();
        return booking;
    }

    @Override
    public long findNumberOfBooking(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        long photoNumber = 0;
        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Booking.findByUserIdNumberOfBooking2").setParameter("id", id);
            photoNumber = (long) q.getSingleResult();
            entityTransaction.commit();

        } catch (NoResultException e) {
            photoNumber = 0;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
        return photoNumber;
    }

    @Override
    public void BookRoom(Date From, Date To, int renterId, int roomId) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        try {
            EntityTransaction entityTrasacrion = em.getTransaction();
            entityTrasacrion.begin();

            Query query = em.createNamedQuery("Room.findById").setParameter("id", roomId);
            Room room = (Room) query.getSingleResult();

            Query query1 = em.createNamedQuery("User.findUserById").setParameter("id", renterId);
            User user = (User) query1.getSingleResult();

            List<Booking> booking = new ArrayList<Booking>();
            Booking b = new Booking();

            b.setDateFrom(From);
            b.setDateTo(To);
            b.setRoomId(room);
            b.setUserId(user);
            booking.add(b);

            user.setBookingList(booking);
            room.setBookingList(booking);

            em.persist(b);

            entityTrasacrion.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public List<Booking> ViewRentings(int id) {

        EntityManager em = EntityManagerHelper.getEntityManager();
        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Booking.findByUserId").setParameter("id", id);
            entityTransaction.commit();
            List<Booking> resultList = q.getResultList();
            return resultList;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public void RateRoom(Booking book) {

        EntityManager em = EntityManagerHelper.getEntityManager();

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();

            Query query = em.createNamedQuery("Booking.findById").setParameter("id", book.getId());
            Booking booking = (Booking) query.getSingleResult();
            booking.setRoomRating(book.getRoomRating());
            booking.setOwnerRating(book.getOwnerRating());

            entityTransaction.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

    }

    public void create(Booking b) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        try {
            EntityTransaction entityTrasacrion = em.getTransaction();
            entityTrasacrion.begin();
            em.persist(b);
            entityTrasacrion.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public Booking findBookingByid(int id, Date date) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Booking.findByIdandDate").setParameter("id", id).setParameter("date", date);
            Booking singleResult = (Booking) q.getSingleResult();
            entityTransaction.commit();
            return singleResult;
        } catch (NoResultException e) {
            return null;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public Booking findBookingDetailsByid(int rentingid) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Booking singleResult = null;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Booking.findByIdforDetails").setParameter("id", rentingid);
            entityTransaction.commit();
            singleResult = (Booking) q.getSingleResult();
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
        EntityManagerHelper.closeEntityManager();
        return singleResult;
    }

    @Override
    public List<Booking> findBookingByroomid(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<Booking> singleResult = null;
        Booking b;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Booking.findByroomId").setParameter("id", id);
            singleResult = q.getResultList();
            entityTransaction.commit();

        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
        EntityManagerHelper.closeEntityManager();

        return singleResult;
    }

    @Override
    public Integer findAvgRating(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        Integer avg = 0;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Booking.findAvgRate").setParameter("id", id);
            Double doubleResult;
            doubleResult = (Double) q.getSingleResult();
            if (doubleResult != null) {
                avg = doubleResult.intValue();
            } else {
                avg = 0;
            }
            entityTransaction.commit();

        } catch (NoResultException e) {
            System.err.print("getSingleResults for findAvgRating Error");
            avg = 0;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            if (em.isOpen()) {
                EntityManagerHelper.closeEntityManager();
            }
        }
        return avg;
    }

    @Override
    public Integer findReviews(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        Integer reviews = 0;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Booking.findReviews").setParameter("id", id);
            Long reviewsResult;
            reviewsResult = (Long) q.getSingleResult();

            reviews = reviewsResult.intValue();

            entityTransaction.commit();

        } catch (NoResultException e) {
            System.err.print("getSingleResults for findReviews Error");
            reviews = 0;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            if (em.isOpen()) {
                EntityManagerHelper.closeEntityManager();
            }
        }
        return reviews;
    }

    @Override
    public List<Integer> findAvgRatingOfOwner(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<Integer> singleResult = null;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Booking.findAvgRateOfOwner").setParameter("id", id);
            singleResult = q.getResultList();
            entityTransaction.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            if (em.isOpen()) {
                EntityManagerHelper.closeEntityManager();
            }
        }
        return singleResult;
    }
}
