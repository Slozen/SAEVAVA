package modele;

import java.util.HashMap;

public class MembreVille {
    private HashMap<String, String> membreVersVille;

    public MembreVille() {
        membreVersVille = new HashMap<>();
    }

    public void ajoutMembre(String membre, String ville) {
        membreVersVille.put(membre, ville);
    }

    public String getVille(String membre) {
        return membreVersVille.get(membre);
    }

    public void afficheMembre() {
        for (String membre : membreVersVille.keySet()) {
            System.out.println(membre + " - habite à : " + membreVersVille.get(membre));
        }
    }

    public HashMap<String, String> getMap() {
        return membreVersVille;
    }
}