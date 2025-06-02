package modele;

import java.util.ArrayList;

public class Scenario {
    private ArrayList<Vente> ventes;

    public Scenario() {
        ventes = new ArrayList<>();
    }

    public void addVente(Vente vente) {
        ventes.add(vente);
    }

    public ArrayList<Vente> getVentes() {
        return ventes;
    }

    public void afficheVentes() {
        for (Vente vente : ventes) {
            System.out.println(vente);
        }
    }

    public String toString() {
        String resultat = "";
        for (Vente vente : ventes) {
            resultat += vente.toString() + "\n";
        }
        return resultat;

    }
}
