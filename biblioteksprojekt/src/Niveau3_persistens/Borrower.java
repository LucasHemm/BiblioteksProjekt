package Niveau3_persistens;

public class Borrower {

    private String adress;
    private String name;
    private int postalCode;

    public Borrower(String adress, String name, int postalCode) {
        this.adress = adress;
        this.name = name;
        this.postalCode = postalCode;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
