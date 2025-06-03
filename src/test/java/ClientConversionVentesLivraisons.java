import lecture.LectureMembres;
import lecture.LectureScenario;
import modele.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClientConversionVentesLivraisons {
    public static void main(String[] args) {
        try {

        File fichierScenario = new File("Scenarios" + File.separator + "scenario_0.txt" );
        File fichierMembres = new File("Membres" + File.separator + "membres_APPLI.txt");

        Scenario scenario = LectureScenario.lectureScenario(fichierScenario);
        MembreVille membres = LectureMembres.lectureMembre(fichierMembres);

        ArrayList<Livraison> livraisons = ConversionVentesLivraisons.conversionVentesLivraisons(scenario, membres);

        for (Livraison livraison : livraisons) {
            System.out.println(livraison);
        }

        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
