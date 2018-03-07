/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Availability;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import jpautils.EntityManagerHelper;


        
//Sos to List den 
public class AvailabilityDAOImpl implements AvailabilityDAO {
    @Override
    public List<Availability> list() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Availability.findAll");
         //Query query = em.createQuery("Select a From Availability a");
        List <Availability> availablityes = query.getResultList();
        return availablityes;
    }

    @Override
    public void create(Availability a) {                
        EntityManager em = EntityManagerHelper.getEntityManager();
        try {
            EntityTransaction entityTrasacrion = em.getTransaction();
            entityTrasacrion.begin();
            em.persist(a);
            entityTrasacrion.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public Availability findAvailabilityById(int id) {
       EntityManager em = EntityManagerHelper.getEntityManager();
        Object singleResult;
        Availability a;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Availability.findById").setParameter("id", id);
            entityTransaction.commit();
            singleResult = q.getSingleResult();
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
        EntityManagerHelper.closeEntityManager();
        a = (Availability) singleResult;
        return a;
    }
}
