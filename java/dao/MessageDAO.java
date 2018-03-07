package dao;

import entities.Message;
import entities.User;
import java.util.List;

public interface MessageDAO {

    public List<Message> list();

    public void create(Message mg);

    public Message findById(int id);

    public List<Message> findMessageByroomid(int id, int userId);

    public List<Message> findAllVistiorMessages(int userId);

    public List<Message> findALLOwnerMessages(int receiverId);

    public List<Message> receivedMessage(int userId);

    public long receivedMessageCounter(int userId);

    public List<Message> receivedMessagePagination(int offset, int recordPerPage, int userId);

    public List<Message> sendedMessage(int id);

    public long sendedMessageCounter(int userId);

    public List<Message> sendedMessagePagination(int offset, int recordPerPage, int id);

    public void DeleteMessage(int messageId, User sessionuser);

}
