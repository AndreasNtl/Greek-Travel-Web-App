package modelwrappers;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import model.BookingModel;

public class BookingWrapper {
    private List<BookingModel> bookings;

    @XmlElement(name="booking")
    public List<BookingModel> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingModel> bookings) {
        this.bookings = bookings;
    }
        
}
