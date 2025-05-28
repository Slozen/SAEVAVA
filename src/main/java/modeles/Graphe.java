package modeles;

import java.util.*;

public class Graphe {
    private TreeMap<String, Set<String>> sommetVoisin;
    private TreeMap<String, Integer> degreEntrant;
    public static final String VELIZY_DEPART = "Velizy+"; //departVelizy
    public static final String VELIZY_RETOUR = "Velizy-"; //arriveeVelizy

    public Graphe(ArrayList<Livraison> livraisons) {
        sommetVoisin = new TreeMap<>();
        degreEntrant = new TreeMap<>();

        Set<String> villesPlus = new HashSet<>();
        Set<String> villesMoins = new HashSet<>();

        // Ajouter les arcs vendeur → acheteur
        for (Livraison livraison : livraisons) {
            String vendeur = livraison.getVilleDepart() + "+";
            String acheteur = livraison.getVilleArrivee() + "-";

            villesPlus.add(vendeur);
            villesMoins.add(acheteur);

            if (!sommetVoisin.containsKey(vendeur)) {
                sommetVoisin.put(vendeur, new TreeSet<>());
            }
            if (!sommetVoisin.containsKey(vendeur)) {
                sommetVoisin.put(acheteur, new TreeSet<>());
            }

            sommetVoisin.get(vendeur).add(acheteur);
        }

        // initialiser tous les degrés entrants à 0
        for (String sommet : sommetVoisin.keySet()) {
            degreEntrant.put(sommet, 0);
        }

        // calcul des degrés entrants
        for (String sommet : sommetVoisin.keySet()) {
            for (String voisin : sommetVoisin.get(sommet)) {
                int deg = degreEntrant.getOrDefault(voisin, 0);
                degreEntrant.put(voisin, deg + 1);
            }
        }

        sommetVoisin.put(VELIZY_DEPART, new TreeSet<>());
        for (String villePlus : villesPlus) {
            sommetVoisin.get(VELIZY_DEPART).add(villePlus);
            degreEntrant.put(villePlus, degreEntrant.get(villePlus) + 1);
        }
        degreEntrant.put(VELIZY_DEPART, 0);

        sommetVoisin.put(VELIZY_RETOUR, new TreeSet<>());
        for (String villeMoins : villesMoins) {
            if (!sommetVoisin.containsKey(villeMoins)) {
                sommetVoisin.put(villeMoins, new TreeSet<>());
            }
            sommetVoisin.get(villeMoins).add(VELIZY_RETOUR);
            degreEntrant.put(VELIZY_RETOUR, degreEntrant.getOrDefault(VELIZY_RETOUR, 0) + 1);
        }
        degreEntrant.putIfAbsent(VELIZY_RETOUR, 0);

    }

    public Set<String> getSommets() {
        return sommetVoisin.keySet();
    }

    public int getOrdre() {
        return sommetVoisin.size();
    }

    public int degre(String sommet) {
        return sommetVoisin.get(sommet).size();
    }

    public int taille() {
        int total = 0;
        for (String s : sommetVoisin.keySet()) {
            total += this.degre(s);
        }
        return total;
    }

    public ArrayList<String> degreEntrantZero(){
        ArrayList<String> degresEntrantZero = new ArrayList<>();
        for (String sommet : sommetVoisin.keySet()){
            if(degreEntrant.get(sommet).equals(0)){
                degresEntrantZero.add(sommet);
            }
        }
        return degresEntrantZero;
    }

    public ArrayList<String> triToploogique(){
        ArrayList<String> sources = degreEntrantZero();
        ArrayList<String> resultat = new ArrayList<>();
        while (!sources.isEmpty()){
            String source = sources.remove(0);
            resultat.add(source);
            for (String voisin : sommetVoisin.get(source)){
                degreEntrant.put(voisin, degreEntrant.get(voisin)-1);
                if (degreEntrant.get(voisin) == 0){
                    sources.add(voisin);
                }
            }
        }
        return resultat;
    }


    public String toString() {
        String affichage = "ordre : " + this.getOrdre() + "\n" + "taille : " + this.taille() + "\n" + "degré entrant : " + degreEntrant.toString() + "\n";
        for (String i : sommetVoisin.keySet()) {
            affichage += "sommet " + i + ", degré sortant = " + this.degre(i) + " voisins : " + sommetVoisin.get(i) + "\n";
        }
        return affichage;
    }
}
