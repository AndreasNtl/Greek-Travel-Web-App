package algorithmhelpers;

public class RecommendationElement implements Comparable<RecommendationElement> {

    private Integer room_id;
    private Float rui;

    public RecommendationElement(Integer room_id, Float rui) {
        this.room_id = room_id;
        this.rui = rui;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public Float getRui() {
        return rui;
    }

    public void setRui(Float rui) {
        this.rui = rui;
    }

    @Override
    public int compareTo(RecommendationElement o) {
        double e = 0.0000001;
        if (this.rui - o.rui > e) {
            return -1;
        } else if (Math.abs(this.rui - o.rui) <= e) {
            return 0;
        } else {
            return +1;
        }
    }

}
