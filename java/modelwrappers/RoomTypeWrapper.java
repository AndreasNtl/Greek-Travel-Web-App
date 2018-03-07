package modelwrappers;

import javax.xml.bind.annotation.XmlElement;
import model.CountryModel;
import model.RoomTypeModel;

public class RoomTypeWrapper {
    private RoomTypeModel type;

    @XmlElement(name="type")
    public RoomTypeModel getType() {
        return type;
    }

    public void setType(RoomTypeModel type) {
        this.type = type;
    }
        
}
