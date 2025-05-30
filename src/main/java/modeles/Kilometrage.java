package modeles;

import java.util.ArrayList;

public class Kilometrage {
    private CarteDistance chCarte;

    public Kilometrage(CarteDistance parCarte) {
        chCarte = parCarte;
    }

    public int calculerDistanceTotale(ArrayList<String> trajet) {
        int total = 0;

        for (int i = 0; i < trajet.size() - 1; i++) {
            String ville1 = trajet.get(i).substring(0, trajet.get(i) .length()-1);
            String ville2 = trajet.get(i + 1).substring(0, trajet.get(i + 1) .length()-1);

            if (chCarte.getVilles().contains(ville1) && chCarte.getVilles().contains(ville2)) {
                total += chCarte.distanceVilles(ville1, ville2);
            }
        }

        return total;
    }

    public void afficherTournee(ArrayList<String> trajet) {
        System.out.println("Tournée :");
        for (String etape : trajet) {
            System.out.print(etape + " → ");
        }
        System.out.println("fin");
        System.out.println("Distance totale : " + calculerDistanceTotale(trajet) + " km");
    }

}
