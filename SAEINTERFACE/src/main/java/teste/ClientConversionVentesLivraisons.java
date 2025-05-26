package teste;

import modele.*;
import lecture.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClientConversionVentesLivraisons {
    public static void main(String[] args) {
        try {

        File fichierScenario = new File("Scenarios" + File.separator + "scenario_0.txt" );
        File fichierMembres = new File("Membres" + File.separator + "membres_APPLI.txt");

        Scenario scenario = LectureScenarios.lectureScenario(fichierScenario);
        Membre membres = LectureMembres.lectureMembre(fichierMembres);

        ArrayList<Livraison> livraisons = ConversionVentesLivraisons.ConversionVentesLivraisons(scenario, membres);

        for (Livraison livraison : livraisons) {
            System.out.println(livraison);
        }

        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
