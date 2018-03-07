package modelwrappers;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import model.RoomModel;
import model.UserModel;

public class RoomWrapper {

    private List<RoomModel> rooms;

    @XmlElement
    public List<RoomModel> getRoom() {
        return rooms;
    }

    public void setRoom(List<RoomModel> rooms) {
        this.rooms = rooms;
    }

}
