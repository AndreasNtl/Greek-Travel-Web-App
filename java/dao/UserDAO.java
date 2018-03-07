package dao;

import entities.Role;
import java.util.List;
import entities.User;

public interface UserDAO {

    public List<User> list();

    public List<Integer> list_ids();

    public void create(User u);

    public long findNumberOfUsers();

    public User findByNickName(String nickName);

    public User findByNickNameWithRoles(String nickName);

    public User findByEmail(String email);

    public User findById(int id);

    public User submitChanges(User user);

    public List<Role> findUserRole(int id);

    public List<User> findAllUserByActivity(int active);

    public User findByEmailWithPhoto(String email);

    public boolean activate(Integer id);

    public boolean addMessage(User visitorMessage, User receiverMessage, String topic, String title, int roomId);

    public User findUserByRoomId(int roomId);

    public List<User> patinagtion(int offset, int recordPerPage);
}
