package dao;

import entities.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import jpautils.EntityManagerHelper;

public class RoleDAOImpl implements RoleDAO {

    @Override
    public List<Role> list() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Role.findAll");
        List<Role> roles = query.getResultList();
        return roles;
    }

    @Override
    public Role findById(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Object singleResult = null;
        Role r;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Role.findById").setParameter("id", id);
            entityTransaction.commit();
            singleResult = q.getSingleResult();
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
        EntityManagerHelper.closeEntityManager();
        r = (Role) singleResult;

        return r;
    }
    
    @Override
    public void create(Role r) {
//        EntityManager em = EntityManagerHelper.getEntityManager();
//        EntityTransaction entityTrasacrion;
//        try {
//            entityTrasacrion = em.getTransaction();
//            entityTrasacrion.begin();
//            em.persist(r);
//            entityTrasacrion.commit();
//            em.close();
//        } catch (RuntimeException e) {
//            if (EntityManagerHelper.getTransaction().isActive()) {
//                EntityManagerHelper.rollback();
//                throw e;
//            }
//        }
//        return null;
    }
    
//    
//    public void register() {
//        EntityManager em = EntityManagerHelper.getEntityManager();
//        
//         EntityTransaction entityTransaction = em.getTransaction();
//         
//         entityTransaction.begin();
//         
//         UserDAOImpl u =new UserDAOImpl(entityTransaction);
//         PhotoDAOImpl p =new PhotoDAOImpl(entityTransaction);
//         
//         u.create(u);
//         entityTransaction.commit();
//    }
}
