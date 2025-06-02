package teste;

import lecture.*;
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
            Membre membres = LectureMembre.lectureMembre(fichierMembres);

            ArrayList<Livraison> livraisons = ConversionVentesLivraisons.conversionVentesLivraisons(scenario, membres);

            File fichier = new File("Distances" + File.separator + "distances.txt" );
            CarteDistance carte = LectureDistance.lectureDistances(fichier);

            Graphe graphe = new Graphe(livraisons);
            System.out.println(graphe);
            System.out.println("tri topologique :");
            System.out.println(graphe.triTopologique());
            System.out.println();
            System.out.println("parcoursHeuristique :");
            System.out.println(graphe.parcoursHeuristique(carte));

        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
