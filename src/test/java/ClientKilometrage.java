import lecture.LectureDistances;
import lecture.LectureMembres;
import lecture.LectureScenario;
import modele.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClientKilometrage {
    public static void main(String[] args) {
        try {
            File fichierScenario = new File("Scenarios" + File.separator + "scenario_0.txt" );
            File fichierMembres = new File("Membres" + File.separator + "membres_APPLI.txt");

            Scenario scenario = LectureScenario.lectureScenario(fichierScenario);
            MembreVille membres = LectureMembres.lectureMembre(fichierMembres);

            ArrayList<Livraison> livraisons = ConversionVentesLivraisons.conversionVentesLivraisons(scenario, membres);

            File fichier = new File("Distances" + File.separator + "distances.txt" );
            CarteDistance carte = LectureDistances.lectureDistances(fichier);

            Graphe graphe = new Graphe(livraisons);
            ArrayList<String> trajet = graphe.parcoursHeuristique(carte);
            ArrayList<String> trajet1 = graphe.triTopologique();

            Kilometrage kilometrage = new Kilometrage(carte);
            Kilometrage kilometrage1 = new Kilometrage(carte);
            kilometrage.afficherTournee(trajet);
            System.out.println();
            kilometrage1.afficherTournee(trajet1);

        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
