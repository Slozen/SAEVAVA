package modele;

import java.util.ArrayList;

public class ConversionVentesLivraisons {
    public static ArrayList<Livraison> conversionVentesLivraisons(Scenario scenario, Membre membres){
        ArrayList<Livraison> livraisons = new ArrayList<>();

        for (Vente ventes : scenario.getVentes()) {
            String villeDepart = membres.getVille(ventes.getVendeur());
            String villeArrivee = membres.getVille(ventes.getAcheteur());

            livraisons.add(new Livraison(villeDepart, villeArrivee));
        }
        return livraisons;
    }
}
