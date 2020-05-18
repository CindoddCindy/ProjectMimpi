package cindodcindy.sirihpinang.prayernote.model;

public class PojoAnsw {

    private int idAnsw;
    private String date_answ;
    private String pray_answ;

    public PojoAnsw(int idAnsw, String date_answ, String pray_answ){
        this.idAnsw=idAnsw;
        this.date_answ=date_answ;
        this.pray_answ=pray_answ;
    }

    public int getIdAnsw() {
        return idAnsw;
    }

    public void setIdAnsw(int idAnsw) {
        this.idAnsw = idAnsw;
    }

    public String getDate_answ() {
        return date_answ;
    }

    public void setDate_answ(String date_answ) {
        this.date_answ = date_answ;
    }

    public String getPray_answ() {
        return pray_answ;
    }

    public void setPray_answ(String pray_answ) {
        this.pray_answ = pray_answ;
    }
}
