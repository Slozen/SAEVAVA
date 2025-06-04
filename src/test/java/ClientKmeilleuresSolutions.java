import modele.*;

import java.io.File;
import java.util.ArrayList;

public class ClientKmeilleuresSolutions {
    public static void main(String[] args) {
        try {
            // Chargement des données nécessaires
            File fichierScenario = new File("Scenarios/scenario_1.txt");
            File fichierMembres = new File("Membres/membres_APPLI.txt");
            File fichierDistances = new File("Distances/distances.txt");

            Scenario scenario = lecture.LectureScenario.lectureScenario(fichierScenario);
            MembreVille membres = lecture.LectureMembres.lectureMembre(fichierMembres);
            CarteDistance carte = lecture.LectureDistances.lectureDistances(fichierDistances);

            ArrayList<Livraison> livraisons = ConversionVentesLivraisons.conversionVentesLivraisons(scenario, membres);

            Graphe graphe = new Graphe(livraisons);

            int k = 5; // on cherche les 5 meilleurs trajets
            KMeilleuresSolutions ksol = new KMeilleuresSolutions(graphe, carte, k);

            ArrayList<Solution> solutions = ksol.calculerMeilleuresSolutions();

            System.out.println("=== K MEILLEURES SOLUTIONS ===");
            for (int i = 0; i < solutions.size(); i++) {
                Solution s = solutions.get(i);
                System.out.println("Solution " + (i + 1) + " :");
                System.out.println("→ Distance totale : " + s.getDistance() + " km");
                System.out.println("→ Trajet : " + s.getTrajet());
                System.out.println();
            }

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}
