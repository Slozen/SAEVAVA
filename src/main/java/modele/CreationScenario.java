package modele;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreationScenario {

    public static void enregistrerScenarioDansFichier(Scenario scenario, File fichier) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fichier));
        for (Ventes v : scenario.getVentes()) {
            writer.write(v.getVendeur() + " -> " + v.getAcheteur());
            writer.newLine();
        }
        writer.close();
    }
}

