package modeles;

import java.util.ArrayList;
import java.util.HashMap;


public class CarteDistance {
    private ArrayList<String> chVilles;
    private HashMap<String, ArrayList<Integer>> chDistances;

    public CarteDistance(ArrayList<String> parVilles, HashMap<String, ArrayList<Integer>> parDistances) {
        chVilles = parVilles;
        chDistances = parDistances;
    }

    public int distanceVilles(String ville1, String ville2) {
        int index = chVilles.indexOf(ville2);
        return chDistances.get(ville1).get(index);
    }

    public ArrayList<String> getVilles() {
        return chVilles;
    }

    public HashMap<String, ArrayList<Integer>> getDistances() {
        return chDistances;
    }

    public void afficheDistances() {
        System.out.println("Liste des villes : " + chVilles);
        System.out.println("Liste des distances : " + chDistances);
    }
}
