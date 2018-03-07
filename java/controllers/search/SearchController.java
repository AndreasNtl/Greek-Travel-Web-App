package controllers.search;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.RoomDAOImpl;
import entities.Room;
import entities.RoomType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.TreeMap;
import validation.AddressValidator;
import validation.CountryNameValidator;
import validation.DateValidator;
import validation.DoublePositiveUperToZero;
import validation.PositiveIntegerValidator;
import validation.StringValidator;
import validation.TypeValidator;

public class SearchController extends controllers.Controller {
    
    private final String target = "/WEB-INF/searchresult.jsp";
    private final String target_error = "/";
    
    private RoomDAOImpl dao = new RoomDAOImpl();
    private BookingDAO bd = new BookingDAOImpl();
    private Map< Room, Integer[]> SearchListFeatures = new HashMap<>();


//    private Comparator<Room> roomComparator = new Comparator<Room>() {
//        @Override 
//        public int compare(Room s1, Room s2) {
//            if (s1.getCostPerDay() < s2.getCostPerDay()) {
//                return -1;
//            } else if (s1.getCostPerDay() == s2.getCostPerDay()) {
//                return 0;
//            } else {
//                return 1;
//            }
//        }           
//    };
//    private Map< Room, Integer[]> SearchListFeatures = new TreeMap<>(roomComparator);
//    private Map< Room, Integer[]> SearchListFeatures = new HashMap<>();
    private String distrinctValue = null;
    private String typeValue = null;
    private String peopleNumValue = null;
    private String costPerDayValue = null;
    private String cityValue = null;
    private String countryValue = null;
    private String addressValue = null;
    
    int currentPage;
    int recordsPerPage = 10;
    int noOfRecords;
    int numberOfPages;
    
    List<Room> list = null;
    
    @Override
    protected boolean validate_input(Map<String, String[]> map) {
        
        StringValidator sv = new StringValidator(errorMap, correctMap);
        CountryNameValidator cnv = new CountryNameValidator(errorMap, correctMap);
        PositiveIntegerValidator piv = new PositiveIntegerValidator(errorMap, correctMap);
        DateValidator dv = new DateValidator(errorMap, correctMap);
        AddressValidator av = new AddressValidator(errorMap, correctMap);
        DoublePositiveUperToZero dpv = new DoublePositiveUperToZero(errorMap, correctMap);
        TypeValidator tv = new TypeValidator(errorMap, correctMap);
        
        typeValue = map.get("type")[0];
        peopleNumValue = map.get("peopleNum")[0];
        costPerDayValue = map.get("costPerDay")[0];
        distrinctValue = map.get("distrinct")[0];
        cityValue = map.get("city")[0];
        countryValue = map.get("country")[0];
        addressValue = map.get("address")[0];
        
        dv.validate(map, "dateFrom");
        dv.validate(map, "dateTo");
        
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
            
            int pNumber = 0;
            double costPday = 0;
            Date startDate = null;
            Date endDate = null;
            
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
            
            noOfRecords = dao.findRoomSearch(correctMap.get("type"), correctMap.get("address"), correctMap.get("country"), correctMap.get("city"), correctMap.get("distrinct"), pNumber, costPday, startDate, endDate).size();
            
            list = dao.findRoomSearchPagination((currentPage - 1) * recordsPerPage, recordsPerPage, correctMap.get("type"), correctMap.get("address"), correctMap.get("country"), correctMap.get("city"), correctMap.get("distrinct"), pNumber, costPday, startDate, endDate);
            
            numberOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            
            for (Room r : list) {
                Integer[] avgAndReviews = new Integer[2];
                
                avgAndReviews[0] = bd.findAvgRating(r.getId());
                avgAndReviews[1] = bd.findReviews(r.getId());
                SearchListFeatures.put(r, avgAndReviews);
            }
            
            
            
            return true;
        } catch (ParseException e) {
            errorMap.addError("exception", e.getMessage());
            return false;
        }
    }
    
    @Override
    protected void on_success() {
        
        if (correctMap.get("type") == null) {
            request.setAttribute("NotInterested", "selected");
        } else if (noOfRecords == 0) {
            request.setAttribute(typeValue, "selected");
        } else if (noOfRecords > 0) {
            for (Room r : list) {
                for (RoomType rt : r.getRoomTypeList()) {
                    request.setAttribute(rt.getType(), "selected");
                }
            }
        }
        request.setAttribute("list", list);
        request.setAttribute("basicSearch", true);
        request.setAttribute("SearchListFeatures", SearchListFeatures);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("numberOfPages", numberOfPages);
        
        redirect(target);
    }
    
    @Override
    protected void on_error() {
        
        redirect(target_error);
    }
}