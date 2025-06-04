package modele;

import java.util.*;

public class KMeilleuresSolutions {

    private final Graphe chGraphe;
    private final CarteDistance chCarte;
    private final int chK;

    public KMeilleuresSolutions(Graphe parGraphe, CarteDistance parCarte, int parK) {
        chGraphe = parGraphe;
        chCarte = parCarte;
        chK = parK;
    }

    public ArrayList<Solution> calculerMeilleuresSolutions() {
        ArrayList<Solution> solutions = new ArrayList<>();
        Set<ArrayList<String>> ordresDejaVus = new HashSet<>();

        int essais = 0;
        int maxEssais = chK * 5;

        while (solutions.size() < chK && essais < maxEssais) {
            essais++;

            ArrayList<String> ordre = chGraphe.triTopologiqueAleatoire();
            if (ordre == null || ordresDejaVus.contains(ordre)) continue;

            int distance = new Kilometrage(chCarte).calculerDistanceTotale(ordre);
            solutions.add(new Solution(ordre, distance));
            ordresDejaVus.add(ordre);
        }

        // Trier par distance croissante
        solutions.sort(Comparator.comparingInt(Solution::getDistance));

        return solutions;
    }
}

