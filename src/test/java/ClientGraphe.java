import lecture.LectureDistances;
import lecture.LectureMembres;
import lecture.LectureScenario;
import modele.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClientGraphe {
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
            System.out.println(graphe);
            System.out.println("tri topologique :");
            System.out.println(graphe.triTopologique());
            System.out.println();
            System.out.println("parcoursHeuristique :");
            System.out.println(graphe.parcoursHeuristique(carte));
            System.out.println();
            System.out.println("tri topologique aleatoire :");
            System.out.println(graphe.triTopologiqueAleatoire());

        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
