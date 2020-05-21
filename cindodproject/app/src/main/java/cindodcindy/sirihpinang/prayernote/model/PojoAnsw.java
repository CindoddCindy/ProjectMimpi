package cindodcindy.sirihpinang.prayernote.model;

public class PojoAnsw {

   private int idAnsw;
   private String date_fr_pray;
   private String pray_fr_pray;
   private String date_answ;
   private String answ_pray;

   public PojoAnsw(){}


   public PojoAnsw(int idAnsw, String date_fr_pray, String pray_fr_pray, String date_answ, String answ_pray){
       this.idAnsw=idAnsw;
       this.date_fr_pray=date_fr_pray;
       this.pray_fr_pray=pray_fr_pray;
       this.date_answ=date_answ;
       this.answ_pray=answ_pray;

   }

    public int getIdAnsw() {
        return idAnsw;
    }

    public void setIdAnsw(int idAnsw) {
        this.idAnsw = idAnsw;
    }

    public String getDate_fr_pray() {
        return date_fr_pray;
    }

    public void setDate_fr_pray(String date_fr_pray) {
        this.date_fr_pray = date_fr_pray;
    }

    public String getPray_fr_pray() {
        return pray_fr_pray;
    }

    public void setPray_fr_pray(String pray_fr_pray) {
        this.pray_fr_pray = pray_fr_pray;
    }

    public String getDate_answ() {
        return date_answ;
    }

    public void setDate_answ(String date_answ) {
        this.date_answ = date_answ;
    }

    public String getAnsw_pray() {
        return answ_pray;
    }

    public void setAnsw_pray(String answ_pray) {
        this.answ_pray = answ_pray;
    }
}
