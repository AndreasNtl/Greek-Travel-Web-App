package dao;

import entities.Photo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import jpautils.EntityManagerHelper;

public class PhotoDAOImpl implements PhotoDAO {

    @Override
    public List<Photo> list() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Photo.findAll");
        List<Photo> photos = query.getResultList();
        return photos;
    }

    @Override
    public List<Photo> pagination(int roomId, int offset, int recordPerPage) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Photo.findByroomId").setFirstResult(offset).setMaxResults(recordPerPage).setParameter("id", roomId);
        List<Photo> photos = query.getResultList();
        return photos;
    }

    @Override
    public void create(Photo ph) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        try {
            EntityTransaction entityTrasacrion = em.getTransaction();
            entityTrasacrion.begin();
            em.persist(ph);
            entityTrasacrion.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public Photo findPhotoById(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Object singleResult;
        Photo ph;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Photo.findById").setParameter("id", id);
            entityTransaction.commit();
            singleResult = q.getSingleResult();
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
        EntityManagerHelper.closeEntityManager();
        ph = (Photo) singleResult;
        return ph;
    }

    @Override
    public String remove(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        String result = null;

        Photo p = em.find(Photo.class, id);
        try {
            if (p == null) {
                result = "not found";
            } else {
                entityTransaction.begin();
                em.remove(p);
                entityTransaction.commit();
                result = "success";
            }
        } finally {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
                result = "rollback";
            }
        }
        return result;
    }

    @Override
    public long findPhotosInRoom(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        long photoNumber = 0;
        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Photo.findNumerOfPhotosInRoom").setParameter("id", id);
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
    public List<Photo> findPhotoByroomId(int roomId) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<Photo> photos;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Photo.findByroomId").setParameter("id", roomId);
            photos = q.getResultList();

            entityTransaction.commit();
            return photos;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public Integer DeletePhoto(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query query = em.createQuery("DELETE FROM Photo p WHERE p.id = :id");
            int deletedCount = query.setParameter("id", id).executeUpdate();
            entityTransaction.commit();
            return deletedCount;
        } catch (NoResultException e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }
}
