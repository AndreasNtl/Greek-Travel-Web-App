package controllers.search;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.RoomDAOImpl;
import dao.UserDAOImpl;
import entities.Role;
import entities.Room;
import entities.RoomType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import validation.AddressValidator;
import validation.CountryNameValidator;
import validation.DateValidator;
import validation.DoublePositiveNegativeValidator;
import validation.DoublePositiveUperToZero;
import validation.PositiveIntegerValidator;
import validation.StringValidator;
import validation.TrueFalseValidator;
import validation.TypeValidator;

public class AdvancedSearchController extends controllers.Controller {

    private RoomDAOImpl dao = new RoomDAOImpl();
    private BookingDAO bd = new BookingDAOImpl();
 
    private final String target = "/WEB-INF/searchAdvancedResult.jsp";
    private final String target_error = "/WEB-INF/searchAdvancedResult.jsp";

    List<Room> list = null;
    List<Role> role = null;

    private Map< Room, Integer[]> SearchListFeatures = new HashMap<>();

    int currentPage;
    int recordsPerPage = 10;
    int noOfRecords;
    int numberOfPages;

    @Override
    protected boolean validate_input(Map<String, String[]> map) {

        DoublePositiveNegativeValidator doublev = new DoublePositiveNegativeValidator(errorMap, correctMap);
        DoublePositiveUperToZero dpv = new  DoublePositiveUperToZero(errorMap, correctMap);
        StringValidator sv = new StringValidator(errorMap, correctMap);
        CountryNameValidator cnv = new CountryNameValidator(errorMap, correctMap);
        PositiveIntegerValidator piv = new PositiveIntegerValidator(errorMap, correctMap);
        DateValidator dv = new DateValidator(errorMap, correctMap);
        AddressValidator av = new AddressValidator(errorMap, correctMap);
        TrueFalseValidator tfv = new TrueFalseValidator(errorMap, correctMap);
        TypeValidator tv = new TypeValidator(errorMap, correctMap);

        String smokingValue = map.get("smoking")[0];
        String petsValue = map.get("pets")[0];
        String eventsValue = map.get("events")[0];
        String wifiValue = map.get("wifi")[0];
        String refridgeratorValue = map.get("refridgerator")[0];
        String parkingValue = map.get("parking")[0];
        String kitchenValue = map.get("kitchen")[0];
        String elevatorValue = map.get("elevator")[0];
        String airconditionValue = map.get("aircondition")[0];
        String heatingValue = map.get("heating")[0];
        String  tvValue = map.get("tv")[0];
        String livingRoomValue = map.get("livingRoom")[0];

        String typeValue = map.get("type")[0];
        String peopleNumValue = map.get("peopleNum")[0];
        String costPerDayValue = map.get("costPerDay")[0];
        String distrinctValue = map.get("distrinct")[0];
        String cityValue = map.get("city")[0];
        String countryValue = map.get("country")[0];
        String addressValue = map.get("address")[0];

        String areaValue = map.get("area")[0];
        String floorValue = map.get("floor")[0];
        String bedNumberValue = map.get("bedNumber")[0];
        String wcNumberValue = map.get("wcNumber")[0];
        String bedroomNumberValue = map.get("bedroomNumber")[0];
        String minimumDaysValue = map.get("minimumDays")[0];

        dv.validate(map, "dateFrom");
        dv.validate(map, "dateTo");

        if ((areaValue != null) && (areaValue.trim().length() >= 1)) {
            dpv.validate(map, "area");
        } else {
            correctMap.put("area", null);
        }

        if ((floorValue != null) && (floorValue.trim().length() >= 1)) {
            piv.validate(map, "floor");
        } else {
            correctMap.put("floor", null);
        }

        if ((minimumDaysValue != null) && (minimumDaysValue.trim().length() >= 1)) {
            piv.validate(map, "minimumDays");
        } else {
            correctMap.put("minimumDays", null);
        }

        if ((wcNumberValue != null) && (wcNumberValue.trim().length() >= 1)) {
            piv.validate(map, "wcNumber");
        } else {
            correctMap.put("wcNumber", null);
        }

        if ((bedNumberValue != null) && (bedNumberValue.trim().length() >= 1)) {
            piv.validate(map, "bedNumber");
        } else {
            correctMap.put("bedNumber", null);
        }

        if ((bedroomNumberValue != null) && (bedroomNumberValue.trim().length() >= 1)) {
            piv.validate(map, "bedroomNumber");
        } else {
            correctMap.put("bedroomNumber", null);
        }

        if ((heatingValue != null) && (heatingValue.trim().length() > 1)) {
            tfv.validate(map, "heating");
        } else {
            correctMap.put("heating", null);
        }

        if ((tvValue != null) && (tvValue.trim().length() > 1)) {
            tfv.validate(map, "tv");
        } else {
            correctMap.put("tv", null);
        }

        if ((livingRoomValue != null) && (livingRoomValue.trim().length() > 1)) {
            tfv.validate(map, "livingRoom");
        } else {
            correctMap.put("livingRoom", null);
        }

        if ((kitchenValue != null) && (kitchenValue.trim().length() > 1)) {
            tfv.validate(map, "kitchen");
        } else {
            correctMap.put("kitchen", null);
        }

        if ((elevatorValue != null) && (elevatorValue.trim().length() > 1)) {
            tfv.validate(map, "elevator");
        } else {
            correctMap.put("elevator", null);
        }

        if ((airconditionValue != null) && (airconditionValue.trim().length() > 1)) {
            tfv.validate(map, "aircondition");
        } else {
            correctMap.put("aircondition", null);
        }

        if ((wifiValue != null) && (wifiValue.trim().length() > 1)) {
            tfv.validate(map, "wifi");
        } else {
            correctMap.put("wifi", null);
        }

        if ((refridgeratorValue != null) && (refridgeratorValue.trim().length() > 1)) {
            tfv.validate(map, "refridgerator");
        } else {
            correctMap.put("refridgerator", null);
        }

        if ((parkingValue != null) && (parkingValue.trim().length() > 1)) {
            tfv.validate(map, "parking");
        } else {
            correctMap.put("parking", null);
        }

        if ((smokingValue != null) && (smokingValue.trim().length() > 1)) {
            tfv.validate(map, "smoking");
        } else {
            correctMap.put("smoking", null);
        }

        if ((petsValue != null) && (petsValue.trim().length() > 1)) {
            tfv.validate(map, "pets");
        } else {
            correctMap.put("pets", null);
        }

        if ((eventsValue != null) && (eventsValue.trim().length() > 1)) {
            tfv.validate(map, "events");
        } else {
            correctMap.put("events", null);
        }

        if ((typeValue != null) && (typeValue.trim().length() > 1)) {
            tv.validate(map, "type");
        } else {
            correctMap.put("type", null);
        }

        if ((countryValue != null) && (countryValue.trim().length() > 1)) {
            cnv.validate(map, "country");
        } else {
            correctMap.put("country", null);
        }

        if ((cityValue != null) && (cityValue.trim().length() > 1)) {
            cnv.validate(map, "city");
        } else {
            correctMap.put("city", null);
        }

        if ((distrinctValue != null) && (distrinctValue.trim().length() > 1)) {
            cnv.validate(map, "distrinct");
        } else {
            correctMap.put("distrinct", null);
        }

        if ((peopleNumValue != null) && (peopleNumValue.trim().length() >= 1)) {
            piv.validate(map, "peopleNum");
        } else {
            correctMap.put("peopleNum", null);
        }

        if ((costPerDayValue != null) && (costPerDayValue.trim().length() >= 1)) {
            dpv.validate(map, "costPerDay");
        } else {
            correctMap.put("costPerDay", null);
        }

        if ((addressValue != null) && (addressValue.trim().length() > 1)) {
            av.validate(map, "address");
        } else {
            correctMap.put("address", null);
        }
        
                
        if (map.get("currentPage") == null) {
            currentPage = 1;
        } else {
            PositiveIntegerValidator pv = new PositiveIntegerValidator(errorMap, correctMap);
            pv.validate(map, "currentPage");
            String stringCurrentPage = correctMap.get("currentPage");
            currentPage = Integer.parseInt(stringCurrentPage);
        }


        return errorMap.isEmpty();
    }

    @Override
    protected boolean validate_logic() {
        return true;
    }

    @Override
    protected boolean try_execute() {

        try {

            int minDays = 0;
            int wcNum = 0;
            int bedNum = 0;
            int bedroomNum = 0;
            Integer flor = 0;
            int pNumber = 0;
            double costPday = 0;
            double ar = 0;

            Date startDate = null;
            Date endDate = null;

            Boolean wf = null;
            Boolean rfr = null;
            Boolean prk = null;
            Boolean ktc = null;
            Boolean elv = null;
            Boolean ac = null;
            Boolean heat = null;
            Boolean tivi = null;
            Boolean li_room = null;
            Boolean smok = null;
            Boolean pet = null;
            Boolean event = null;

            if (correctMap.get("area") != null && !correctMap.get("area").isEmpty()) {
                ar = Double.parseDouble(correctMap.get("area"));
            }
            if (correctMap.get("minimumDays") != null && !correctMap.get("minimumDays").isEmpty()) {
                minDays = Integer.parseInt(correctMap.get("minimumDays"));
            }
            if (correctMap.get("floor") != null && !correctMap.get("floor").isEmpty()) {
                flor = Integer.parseInt(correctMap.get("floor"));
            }
            if (correctMap.get("bedNumber") != null && !correctMap.get("bedNumber").isEmpty()) {
                bedNum = Integer.parseInt(correctMap.get("bedNumber"));
            }
            if (correctMap.get("bedroomNumber") != null && !correctMap.get("bedroomNumber").isEmpty()) {
                bedroomNum = Integer.parseInt(correctMap.get("bedroomNumber"));
            }
            if (correctMap.get("wcNumber") != null && !correctMap.get("wcNumber").isEmpty()) {
                wcNum = Integer.parseInt(correctMap.get("wcNumber"));
            }
            if (correctMap.get("peopleNum") != null && !correctMap.get("peopleNum").isEmpty()) {
                pNumber = Integer.parseInt(correctMap.get("peopleNum"));
            }
            if (correctMap.get("costPerDay") != null && !correctMap.get("costPerDay").isEmpty()) {
                costPday = Double.parseDouble(correctMap.get("costPerDay"));
            }
            if (correctMap.get("dateFrom") != null && !correctMap.get("dateFrom").isEmpty()) {
                DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                startDate = (Date) formatter.parse(correctMap.get("dateFrom"));
            }
            if (correctMap.get("dateTo") != null && !correctMap.get("dateTo").isEmpty()) {
                DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                endDate = (Date) formatter.parse(correctMap.get("dateTo"));
            }
            if (correctMap.get("events") != null && !correctMap.get("events").isEmpty()) {
                event = Boolean.valueOf(correctMap.get("events"));
            }
            if (correctMap.get("livingRoom") != null && !correctMap.get("livingRoom").isEmpty()) {
                li_room = Boolean.valueOf(correctMap.get("livingRoom"));
            }
            if (correctMap.get("smoking") != null && !correctMap.get("smoking").isEmpty()) {
                smok = Boolean.valueOf(correctMap.get("smoking"));
            }
            if (correctMap.get("pets") != null && !correctMap.get("pets").isEmpty()) {
                pet = Boolean.valueOf(correctMap.get("pets"));
            }

            if (correctMap.get("aircondition") != null && !correctMap.get("aircondition").isEmpty()) {
                ac = Boolean.valueOf(correctMap.get("aircondition"));
            }
            if (correctMap.get("heating") != null && !correctMap.get("heating").isEmpty()) {
                heat = Boolean.valueOf(correctMap.get("heating"));
            }
            if (correctMap.get("tv") != null && !correctMap.get("tv").isEmpty()) {
                tivi = Boolean.valueOf(correctMap.get("tv"));
            }
            if (correctMap.get("parking") != null && !correctMap.get("parking").isEmpty()) {
                prk = Boolean.valueOf(correctMap.get("parking"));
            }
            if (correctMap.get("kitchen") != null && !correctMap.get("kitchen").isEmpty()) {
                ktc = Boolean.valueOf(correctMap.get("kitchen"));
            }
            if (correctMap.get("elevator") != null && !correctMap.get("elevator").isEmpty()) {
                elv = Boolean.valueOf(correctMap.get("elevator"));
            }

            if (correctMap.get("refridgerator") != null && !correctMap.get("refridgerator").isEmpty()) {
                rfr = Boolean.valueOf(correctMap.get("refridgerator"));
            }
            if (correctMap.get("wifi") != null && !correctMap.get("wifi").isEmpty()) {
                wf = Boolean.valueOf(correctMap.get("wifi"));
                
            }
            
              //ayta prepei na panw pio panw oxi edw
            if ((endDate != null && startDate != null)) {
                if (startDate.compareTo(endDate) > 0 || startDate.compareTo(endDate) == 0) {
                    errorMap.addError("dateTo", "Insert date afte dateFrom");
                    return false;
                }
            } else {
                errorMap.addError("dateTo", "missing");
                errorMap.addError("dateFrom", "missing");
            }
            
            noOfRecords = dao.findAdvancedRoomSearch(correctMap.get("type"), correctMap.get("address"), correctMap.get("country"),
                    correctMap.get("city"), correctMap.get("distrinct"), pNumber, costPday, startDate, endDate, wf, rfr, ac, heat,
                    ktc, tivi, prk, elv, li_room, smok, ar, flor, bedNum, bedroomNum, pet, event, wcNum, minDays).size();

            list = dao.findAdvancedRoomSearchPagination((currentPage -1) *  recordsPerPage, recordsPerPage, correctMap.get("type"), correctMap.get("address"), correctMap.get("country"),
                    correctMap.get("city"), correctMap.get("distrinct"), pNumber, costPday, startDate, endDate, wf, rfr, ac, heat,
                    ktc, tivi, prk, elv, li_room, smok, ar, flor, bedNum, bedroomNum, pet, event, wcNum, minDays);

            numberOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            for (Room r : list) {
                Integer[] avgAndReviews = new Integer[2];

                avgAndReviews[0] = bd.findAvgRating(r.getId());
                avgAndReviews[1] = bd.findReviews(r.getId());

                SearchListFeatures.put(r, avgAndReviews);
            }

            return true;
        } catch (Exception e) {
            list = new ArrayList<>();

            errorMap.addError("exception", e.getMessage());
            return false;
        }
    }

    @Override
    protected void on_success() {
        for(Room r : list){
            System.out.println(r + "\n");
        }

        if (correctMap.get("type") == null) {
            request.setAttribute("NotInterested", "selected");
        } else if( noOfRecords == 0){
            request.setAttribute(correctMap.get("type"), "selected");
        } else  if( noOfRecords > 0) {
            for (Room r : list) {
                for (RoomType rt : r.getRoomTypeList()) {
                    request.setAttribute(rt.getType(), "selected");
                }
            }
        }

        request.setAttribute("advancedSearch", true);
        request.setAttribute("list", list);
        request.setAttribute("SearchListFeatures", SearchListFeatures);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("numberOfPages", numberOfPages);

        redirect(target);
    }

    @Override
    protected void on_error() {

        request.setAttribute("list", list);
        redirect(target_error);
    }
}