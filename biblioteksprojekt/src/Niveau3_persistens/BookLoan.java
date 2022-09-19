package Niveau3_persistens;

public class BookLoan {

    private int bogid;
    private int lånerid;

    public BookLoan(int bogid, int lånerid) {
        this.bogid = bogid;
        this.lånerid = lånerid;
    }

    public int getBogid() {
        return bogid;
    }

    public void setBogid(int bogid) {
        this.bogid = bogid;
    }

    public int getLånerid() {
        return lånerid;
    }

    public void setLånerid(int lånerid) {
        this.lånerid = lånerid;
    }
}
