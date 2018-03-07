package dao;

import entities.Booking;
import entities.Message;
import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import jpautils.EntityManagerHelper;

public class MessageDAOImpl implements MessageDAO {

    @Override
    public List<Message> list() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("Message.findAll");
        List<Message> messeges = query.getResultList();
        return messeges;
    }

    @Override
    public void create(Message mg) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        try {
            EntityTransaction entityTrasacrion = em.getTransaction();
            entityTrasacrion.begin();
            em.persist(mg);
            entityTrasacrion.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public Message findById(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Object singleResult;
        Message mg;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Message.findById").setParameter("id", id);
            entityTransaction.commit();
            singleResult = q.getSingleResult();
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
        EntityManagerHelper.closeEntityManager();
        mg = (Message) singleResult;
        return mg;
    }

    @Override
    public List<Message> findAllVistiorMessages(int userId) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTrasacriony;

        try {
            entityTrasacriony = em.getTransaction();
            entityTrasacriony.begin();
            Query q = em.createQuery("select r from Message r join r.senderId c where c.id = :senderId").setParameter("senderId", userId);
            entityTrasacriony.commit();
            List<Message> result = q.getResultList();

            if (result == null) {
                return null;
            } else {
                return result;
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public List<Message> findALLOwnerMessages(int ownerId) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTrasacriony;

        try {
            entityTrasacriony = em.getTransaction();
            entityTrasacriony.begin();
            Query q = em.createQuery("select r from Message r join r.receiverId c where c.id = :receiverId").setParameter("receiverId", ownerId);
            entityTrasacriony.commit();
            List<Message> result = q.getResultList();

            if (result == null) {
                return null;
            } else {
                return result;
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public List<Message> receivedMessage(int userId) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTrasacriony;

        try {
            entityTrasacriony = em.getTransaction();
            entityTrasacriony.begin();
            Query q;
            q = em.createQuery("SELECT m FROM Message m inner join fetch m.senderId us inner join fetch m.receiverId ur where ur.id = :id and m.receiverDeletes = false").setParameter("id", userId);
            entityTrasacriony.commit();
            List<Message> result = q.getResultList();

            if (result == null) {
                return null;
            } else {
                return result;
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public long receivedMessageCounter(int userId) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        long numberOfMessages = 0;
        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Message.findNumberOfIncommingMessage").setParameter("id", userId);
            numberOfMessages = (long) q.getSingleResult();
            entityTransaction.commit();

        } catch (NoResultException e) {
            numberOfMessages = 0;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
        return numberOfMessages;
    }

    @Override
    public List<Message> receivedMessagePagination(int offset, int recordPerPage, int userId) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTrasacriony;

        try {
            entityTrasacriony = em.getTransaction();
            entityTrasacriony.begin();
            Query q;
            q = em.createQuery("SELECT m FROM Message m inner join fetch m.senderId us inner join fetch m.receiverId ur where ur.id = :id and m.receiverDeletes = false")
                    .setFirstResult(offset)
                    .setMaxResults(recordPerPage)
                    .setParameter("id", userId);
            entityTrasacriony.commit();
            List<Message> result = q.getResultList();

            if (result == null) {
                return null;
            } else {
                return result;
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public List<Message> sendedMessage(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction;

        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q;
            q = em.createQuery("SELECT m FROM Message m inner join fetch m.senderId us inner join fetch m.receiverId ur where us.id =:id and m.senderDeletes = false")
                    .setParameter("id", id);
            List<Message> result = q.getResultList();

            entityTransaction.commit();
            if (result == null) {
                return null;
            } else {
                return result;
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public long sendedMessageCounter(int userId) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        long numberOfMessages = 0;
        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Message.findNumberOfOutgoingMessage").setParameter("id", userId);
            numberOfMessages = (long) q.getSingleResult();
            entityTransaction.commit();

        } catch (NoResultException e) {
            numberOfMessages = 0;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
        return numberOfMessages;
    }

    @Override
    public List<Message> sendedMessagePagination(int offset, int recordPerPage, int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction;

        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q;
            q = em.createQuery("SELECT m FROM Message m inner join fetch m.senderId us inner join fetch m.receiverId ur where us.id =:id and m.senderDeletes = false")
                    .setFirstResult(offset)
                    .setMaxResults(recordPerPage)
                    .setParameter("id", id);
            List<Message> result = q.getResultList();

            entityTransaction.commit();
            if (result == null) {
                return null;
            } else {
                return result;
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public List<Message> findMessageByroomid(int id, int userId) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<Message> singleResult = null;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Message.findByroomId").setParameter("id", id).setParameter("userId", userId);

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
    public void DeleteMessage(int messageId, User sessionuser) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction;

        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("Message.findById").setParameter("id", messageId);
            Message message = (Message) q.getSingleResult();

            int sessionuserId = sessionuser.getId();
            int messageSenderId = message.getSenderId().getId();
            int messageReceiverId = message.getReceiverId().getId();
            if (sessionuserId == messageSenderId) {
                message.setSenderDeletes(true);
            } else if (sessionuserId == messageReceiverId) {
                message.setReceiverDeletes(true);
            }
            if (message.getReceiverDeletes() && message.getSenderDeletes()) {
                Query query = em.createQuery("DELETE FROM Message m WHERE m.id = :id");
                query.setParameter("id", messageId).executeUpdate();
            }
            entityTransaction.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

    }
}

// to do remove a message ,find converseion beetween owner and visitor

