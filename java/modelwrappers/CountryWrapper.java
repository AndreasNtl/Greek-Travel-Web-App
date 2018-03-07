package modelwrappers;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import model.CountryModel;

public class CountryWrapper {
    private CountryModel country;

    @XmlElement(name="country")
    public CountryModel getCountry() {
        return country;
    }

    public void setCountry(CountryModel country) {
        this.country = country;
    }
        
}
