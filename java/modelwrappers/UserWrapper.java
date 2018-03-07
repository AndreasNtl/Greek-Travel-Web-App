package modelwrappers;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import model.UserModel;

public class UserWrapper {
    private List<UserModel> users;

    @XmlElement(name="user")
    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
    
    
}
