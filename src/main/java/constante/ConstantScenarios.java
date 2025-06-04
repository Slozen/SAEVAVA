package constante;

import java.io.File;
import java.util.ArrayList;

public class ConstantScenarios {

    public static String[] getListeScenarios() {
        File dossier = new File("Scenarios");
        ArrayList<String> liste = new ArrayList<>();

        if (dossier.exists() && dossier.isDirectory()) {
            for (File f : dossier.listFiles()) {
                if (f.getName().endsWith(".txt")) {
                    // Retirer ".txt" pour l'affichage
                    String nom = f.getName().substring(0, f.getName().length() - 4);
                    liste.add(nom.replace("_", " "));
                }
            }
        }

        return liste.toArray(new String[0]);
    }
}
