/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Location;
import entities.Room;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import jpautils.EntityManagerHelper;

public class LocationDAOImpl implements LocationDAO {

    @Override
    public List<Location> list() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Location.findAll");
        List<Location> locations = query.getResultList();
        return locations;
    }

    @Override
    public void create(Location l) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        try {
            EntityTransaction entityTrasacrion = em.getTransaction();
            entityTrasacrion.begin();
            em.persist(l);
            entityTrasacrion.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public Location findLocationById(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Object singleResult;
        Location l;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Location.findById").setParameter("id", id);
            entityTransaction.commit();
            singleResult = q.getSingleResult();
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
        EntityManagerHelper.closeEntityManager();
        l = (Location) singleResult;
        return l;
    }

    //To do find by Room
    @Override
    public Location findLocationByTheRoomId(int roomId) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTrasacriony;
        Object singleResult = null;
        Location l;

        try {
            entityTrasacriony = em.getTransaction();
            entityTrasacriony.begin();
            Query q = em.createQuery("select u from Location u where u.roomId.id = :id").setParameter("id", roomId);
            entityTrasacriony.commit();
            singleResult = q.getSingleResult();
           
        } catch (NoResultException e) {
            l = null;
            EntityManagerHelper.closeEntityManager();
        } catch (RuntimeException e) {
            throw e;
        }
        EntityManagerHelper.closeEntityManager();
        l = (Location) singleResult;
        return l;
    }

}
