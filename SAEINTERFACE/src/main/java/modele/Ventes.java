package modele;

public class Ventes {
    private String chVendeur;
    private String chAcheteur;

    public Ventes(String parVendeur , String parAcheteur) {
        chVendeur = parVendeur;
        chAcheteur = parAcheteur;
    }

    public String getVendeur() {
        return chVendeur;
    }

    public String getAcheteur() {
        return chAcheteur;
    }

    public String toString() {
        return "vendeur = " + chVendeur + ", acheteur = " + chAcheteur;
    }
}
