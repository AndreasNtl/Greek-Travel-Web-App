package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import modelwrappers.RoomWrapper;

@XmlRootElement
public class Export {
//    private UserWrapper users;
    private RoomWrapper rooms;

    @XmlElement
    public RoomWrapper getRooms() {
        return rooms;
    }

    public void setRooms(RoomWrapper rooms) {
        this.rooms = rooms;
    }
}
