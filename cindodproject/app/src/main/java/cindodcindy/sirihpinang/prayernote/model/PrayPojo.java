package cindodcindy.sirihpinang.prayernote.model;

public class PrayPojo {

    private int prayId;
    private String date;
    private String pray;
    private String date_ans;
    private String pray_answ;

    public PrayPojo(int prayId, String date, String pray, String date_ans, String pray_answ){
        this.prayId=prayId;
        this.date=date;
        this.pray=pray;
        this.date_ans=date_ans;
        this.pray_answ=pray_answ;
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

    public String getDate_ans() {
        return date_ans;
    }

    public void setDate_ans(String date_ans) {
        this.date_ans = date_ans;
    }

    public String getPray_answ() {
        return pray_answ;
    }

    public void setPray_answ(String pray_answ) {
        this.pray_answ = pray_answ;
    }
}
