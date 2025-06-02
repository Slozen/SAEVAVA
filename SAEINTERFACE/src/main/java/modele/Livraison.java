package modele;

public class Livraison {
    private String chVilleDepart;
    private String chVilleArrivee;

    public Livraison(String parVilleDepart, String parVilleArrivee) {
        chVilleDepart = parVilleDepart;
        chVilleArrivee = parVilleArrivee;
    }

    public String getVilleDepart() {
        return chVilleDepart;
    }

    public String getVilleArrivee() {
        return chVilleArrivee;
    }

    public String toString() {
        return chVilleDepart + " -> " + chVilleArrivee;
    }
}

