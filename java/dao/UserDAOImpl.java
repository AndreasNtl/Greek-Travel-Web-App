package dao;

import entities.Message;
import entities.Photo;
import entities.Role;
import entities.Room;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpautils.EntityManagerHelper;
import entities.User;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> list() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("User.findAll");
        List<User> users = query.getResultList();
        EntityManagerHelper.closeEntityManager();
        return users;
    }

    public List<User> listWithRoles() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("User.findAllWithRoles");
        List<User> users = query.getResultList();
        EntityManagerHelper.closeEntityManager();
        return users;
    }

    @Override
    public void create(User u) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        try {
            EntityTransaction entityTrasacrion = em.getTransaction();
            entityTrasacrion.begin();
            em.persist(u);
            entityTrasacrion.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public long findNumberOfUsers() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        long numberOfUsers = 0;
        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("User.findNumberOfUsers");
            numberOfUsers = (long) q.getSingleResult();
            entityTransaction.commit();
        } catch (NoResultException e) {
            numberOfUsers = 0;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
        return numberOfUsers;
    }

    public Integer createWithPhoto(User u) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        EntityTransaction entityTrasacrion = null;

        try {
            entityTrasacrion = em.getTransaction();
            entityTrasacrion.begin();

            // **************************************************
            //                  Role assignment
            // **************************************************
            List<Role> insertedRoles = new ArrayList<>();
            List<User> usersInrole;

            Query q = em.createNamedQuery("Role.findAllVisitor").setParameter("id", u.getRoleList().get(0).getId());
            Role singleResult = (Role) q.getSingleResult();
            usersInrole = singleResult.getUserList();// kratao ollous tous user ston rolo visitor
            insertedRoles.add(singleResult);
            u.getRoleList().clear();

            usersInrole.add(u);
            u.setRoleList(insertedRoles);

            em.persist(u);
//              List<Role> insertedRoles = new ArrayList<>();
//            for (Role r : u.getRoleList()) {
//                Query q = em.createNamedQuery("Role.findById").setParameter("id", r.getId());
//                Role singleResult = (Role) q.getSingleResult();//brikame ton rolo 
//                singleResult.setUserList(new ArrayList<>());
//                insertedRoles.add(singleResult);
//            }
//
//            u.getRoleList().clear();
//
//            for (Role r : insertedRoles) {
//                u.getRoleList().add(r);
//                r.getUserList().add(u);
//            }
            // insert user
//            u.setPhotoList(new ArrayList<Photo>());
            // **************************************************
            //                  Photo assignment
            // **************************************************
            for (Photo p : u.getPhotoList()) {
                List<User> userList = new ArrayList<>();
                userList.add(u);
                p.setUserList(userList);

//                u.getPhotoList().add(p);
                em.persist(p);
            }

            entityTrasacrion.commit();
        } catch (NoResultException e) {

        } catch (RuntimeException e) {
            if (entityTrasacrion != null && entityTrasacrion.isActive()) {
                entityTrasacrion.rollback();
            }
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
        return u.getId();
    }

    @Override
    public boolean activate(Integer id) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();

            Query q = em.createNamedQuery("User.findByIdAndRoles").setParameter("id", id);
            User u = (User) q.getSingleResult();
            u.setActive(0);

            Query q2 = em.createNamedQuery("Role.findById").setParameter("id", 2);
            Role r = (Role) q2.getSingleResult();

            u.getRoleList().add(r);

            r.getUserList().add(u);

            entityTransaction.commit();
//            EntityManagerHelper.closeEntityManager();

            return true;
        } catch (NoResultException e) {
            return false;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    @Override
    public User findByNickName(String nickName) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Object singleResult = null;
        User u;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("User.findByNickname").setParameter("nickname", nickName);
            entityTransaction.commit();
            singleResult = q.getSingleResult();
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
        EntityManagerHelper.closeEntityManager();
        u = (User) singleResult;

        return u;
    }

    @Override
    public User findByNickNameWithRoles(String nickName) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Object singleResult = null;
        User u;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("User.findByNicknameAndRoles").setParameter("nickname", nickName);
            entityTransaction.commit();
            singleResult = q.getSingleResult();
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
        EntityManagerHelper.closeEntityManager();
        u = (User) singleResult;

        return u;
    }

    @Override
    public User findByEmail(String email) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Object singleResult = null;
        User u;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("User.findByEmail").setParameter("email", email);
            entityTransaction.commit();
            singleResult = q.getSingleResult();
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
        EntityManagerHelper.closeEntityManager();
        u = (User) singleResult;

        return u;
    }

    @Override
    public User findById(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        User u = null;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("User.findById").setParameter("id", id);
            u = (User) q.getSingleResult();

            entityTransaction.commit();
        } catch (NoResultException e) {
            u = null;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

        return u;
    }

    @Override
    public List<Role> findUserRole(int id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTrasacriony;

        try {
            entityTrasacriony = em.getTransaction();
            entityTrasacriony.begin();
            Query q = em.createQuery("select rl.role from User u inner join u.roleList rl where u.id = :id").setParameter("id", id);
            entityTrasacriony.commit();
            List<Role> result = q.getResultList();

            if (result == null || result.isEmpty()) {
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
    public List<User> findAllUserByActivity(int active) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTrasacriony;

        try {
            entityTrasacriony = em.getTransaction();
            entityTrasacriony.begin();
            Query query = em.createNamedQuery("User.findByActive").setParameter("active", active);
            entityTrasacriony.commit();
            List<User> result = query.getResultList();

            if (result != null) {
                return result;
            } else {
                return null;
            }
        } catch (RuntimeException e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

    }

    @Override
    public User findByEmailWithPhoto(String email) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Object singleResult = null;
        User u;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createQuery("SELECT u FROM User u inner join fetch u.photoList WHERE u.email =:email").setParameter("email", email);
            entityTransaction.commit();
            singleResult = q.getSingleResult();
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
        EntityManagerHelper.closeEntityManager();
        u = (User) singleResult;

        return u;
    }

    @Override
    public User submitChanges(User newuser) {

        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction = null;
        User olduser = null;

        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("User.findByNickname").setParameter("nickname", newuser.getNickname());
            olduser = (User) q.getSingleResult();

            if (newuser.getFirstName() != null && !newuser.getFirstName().isEmpty()) {
                olduser.setFirstName(newuser.getFirstName());
            }
            if (newuser.getSurname() != null && !newuser.getSurname().isEmpty()) {
                olduser.setSurname(newuser.getSurname());
            }
            if (newuser.getEmail() != null && !newuser.getEmail().isEmpty()) {
                olduser.setEmail(newuser.getEmail());
            }
            if (newuser.getPassword() != null && !newuser.getPassword().isEmpty()) {
                olduser.setPassword(newuser.getPassword());
            }
            if (newuser.getPhoneNumber() != null && !newuser.getPhoneNumber().isEmpty()) {
                olduser.setPhoneNumber(newuser.getPhoneNumber());
            }

            System.out.println(olduser.toString());

            if (!newuser.getPhotoList().isEmpty() && newuser.getPhotoList().get(0).getPhotographUrl() != null) {
                Photo newphoto = newuser.getPhotoList().get(0);
                Photo oldphoto = olduser.getPhotoList().get(0);

                Query q2 = em.createNamedQuery("Photo.findById").setParameter("id", oldphoto.getId());
                oldphoto = (Photo) q2.getSingleResult();
                oldphoto.setPhotographUrl(newphoto.getPhotographUrl());
            }

            entityTransaction.commit();
            return olduser;
        } catch (RuntimeException e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

    }

    @Override
    public List<User> patinagtion(int offset, int recordPerPage) {

        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createQuery("select u from User u inner join fetch u.photoList inner join fetch u.photoList order by u.active desc").setFirstResult(offset).setMaxResults(recordPerPage);
        List<User> users = query.getResultList();
        EntityManagerHelper.closeEntityManager();
        return users;
    }

    @Override
    public boolean addMessage(User senderMessage, User receiverMessage, String topic, String title, int roomId) {

        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();

            int senderID = senderMessage.getId();
            Query q = em.createNamedQuery("User.findById").setParameter("id", senderID);
            User sender = (User) q.getSingleResult();

            int receiverID = receiverMessage.getId();
            Query q1 = em.createNamedQuery("User.findById").setParameter("id", receiverID);
            User receiver = (User) q1.getSingleResult();

            Query q2 = em.createNamedQuery("Room.findById").setParameter("id", roomId);
            Room ownerRoom = (Room) q2.getSingleResult();
            List<Message> message = new ArrayList<Message>();
            Message m = new Message();

            m.setText(title);
            m.setTopic(topic);
            m.setSubmittedDate(new Date());
            m.setReceiverDeletes(false);
            m.setSenderDeletes(false);
            m.setReceiverId(receiver);
            m.setSenderId(sender);
            m.setRoomId(ownerRoom);

            message.add(m);

            receiver.setMessageList1(message);
            sender.setMessageList(message);
            ownerRoom.setMessageList(message);

            em.persist(m);

            entityTransaction.commit();

            return true;
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return false;
        } catch (RuntimeException e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

    }

    @Override
    public User findUserByRoomId(int roomId) {

        EntityManager em = EntityManagerHelper.getEntityManager();
        Object singleResult;
        User u = null;

        try {
            EntityTransaction entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query q = em.createNamedQuery("User.findByRoomId").setParameter("id", roomId);
            entityTransaction.commit();

            singleResult = q.getSingleResult();
            u = (User) singleResult;
        } catch (NoResultException e) {
            EntityManagerHelper.closeEntityManager();
            return null;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            if (em.isOpen()) {
                EntityManagerHelper.closeEntityManager();
            }
        }
        return u;
    }

    public List<Integer> list_ids() {
        List<Integer> ids = new ArrayList<>();

        EntityManager em = EntityManagerHelper.getEntityManager();
        Query query = em.createNamedQuery("User.findAll");
        List<User> users = query.getResultList();
        EntityManagerHelper.closeEntityManager();

        for (User u : users) {
            ids.add(u.getId());
        }

        return ids;
    }
}
