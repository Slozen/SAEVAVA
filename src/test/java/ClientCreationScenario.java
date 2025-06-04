import modele.CreationScenario;
import modele.Scenario;
import modele.Ventes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClientCreationScenario {
    public static void main(String[] args) {
        ArrayList<Ventes> ventes = new ArrayList<>();
        ventes.add(new Ventes("Abra", "Grotadmorv"));
        ventes.add(new Ventes("Tengalice", "Sulfura"));
        ventes.add(new Ventes("Lombre", "Tentacool"));

        Scenario scenario = new Scenario();
        for (Ventes v : ventes) {
            scenario.addVente(v);
        }

        System.out.println("Scénario à enregistrer :");
        scenario.afficheVentes();

        File fichier = new File("Scenarios/scenario_test.txt");
        try {
            CreationScenario.enregistrerScenarioDansFichier(scenario, fichier);
            System.out.println("Scénario enregistré dans : " + fichier.getPath());
        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }
}
