package cindodcindy.sirihpinang.prayernote.model;

public class PrayPojo {

    private int prayId;
    private String date;
    private String pray;

    public PrayPojo(int prayId, String date, String pray){
        this.prayId=prayId;
        this.date=date;
        this.pray=pray;

    }

    public PrayPojo(String date, String pray){
        this.date=date;
        this.pray=pray;
    }



    public int getPrayId() {
        return prayId;
    }

    public void setPrayId(int prayId) {
        this.prayId = prayId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPray() {
        return pray;
    }

    public void setPray(String pray) {
        this.pray = pray;
    }


}
