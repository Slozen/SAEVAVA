package modeles;

import java.util.ArrayList;

public class Scenario {
    private ArrayList<Ventes> ventes;

    public Scenario() {
        ventes = new ArrayList<>();
    }

    public void addVente(Ventes vente) {
        ventes.add(vente);
    }

    public ArrayList<Ventes> getVentes() {
        return ventes;
    }

    public void afficheVentes() {
        for (Ventes vente : ventes) {
            System.out.println(vente);
        }
    }

}
