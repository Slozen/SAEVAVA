import modeles.*;

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

            ArrayList<Livraison> livraisons = ConversionVentesLivraisons.ConversionVentesLivraisons(scenario, membres);

            Graphe graphe = new Graphe(livraisons);
            System.out.println(graphe);
            System.out.println("tri topologique");
            System.out.println();
            System.out.println(graphe.triToploogique());

        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
