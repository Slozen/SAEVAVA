package modele;

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

    public ArrayList<String> triTopologique(){
        ArrayList<String> sources = degreEntrantZero();
        ArrayList<String> resultat = new ArrayList<>();

        HashMap<String, Integer> degEntrant = new HashMap<>();
        for (String sommet : degreEntrant.keySet()) {
            degEntrant.put(sommet, degreEntrant.get(sommet));
        }

        while (!sources.isEmpty()){
            String source = sources.remove(0);
            resultat.add(source);
            for (String voisin : sommetVoisin.get(source)){
                degEntrant.put(voisin, degEntrant.get(voisin)-1);
                if (degEntrant.get(voisin) == 0){
                    sources.add(voisin);
                }
            }
        }
        return resultat;
    }

    public ArrayList<String> parcoursHeuristique(CarteDistance carte) {
        ArrayList<String> trajet = new ArrayList<>();
        Set<String> visites = new HashSet<>();

        HashMap<String, Integer> degEntrantRestant = new HashMap<>();
        for (String sommet : degreEntrant.keySet()) {
            degEntrantRestant.put(sommet, degreEntrant.get(sommet));
        }

        String positionActuelle = VELIZY_DEPART;
        trajet.add(positionActuelle);
        visites.add(positionActuelle);

        while (!positionActuelle.equals(VELIZY_RETOUR)) {

            Set<String> voisins = sommetVoisin.get(positionActuelle);
            for (String voisin : voisins) {
                int deg = degEntrantRestant.get(voisin);
                degEntrantRestant.put(voisin, deg - 1);
            }

            ArrayList<String> disponibles = new ArrayList<>();
            for (String sommet : degEntrantRestant.keySet()) {
                if (degEntrantRestant.get(sommet) == 0 && !visites.contains(sommet)) {
                    disponibles.add(sommet);
                }
            }
           /* System.out.println("ville disp : " + disponibles );*/

            String prochain = disponibles.get(0);
            int minDistance = carte.distanceVilles(positionActuelle.substring(0, positionActuelle.length()-1),
                    disponibles.get(0).substring(0, disponibles.get(0).length()-1));

            for (String ville : disponibles) {
                String villeActuelle = positionActuelle.substring(0, positionActuelle.length()-1);
                String villeCible = ville.substring(0, ville.length()-1);

                if (carte.getVilles().contains(villeActuelle) && carte.getVilles().contains(villeCible)) {
                    int dist = carte.distanceVilles(villeActuelle, villeCible);
                    if (dist < minDistance) {
                        minDistance = dist;
                        prochain = ville;
                    }
                }
                /*System.out.println(disponibles);*/
            }

            positionActuelle = prochain;
            visites.add(positionActuelle);
            trajet.add(positionActuelle);
        }
        return trajet;
    }




    public String toString() {
        String affichage = "ordre : " + this.getOrdre() + "\n" + "taille : " + this.taille() + "\n" + "degré entrant : " + degreEntrant.toString() + "\n";
        for (String i : sommetVoisin.keySet()) {
            affichage += "sommet " + i + ", degré sortant = " + this.degre(i) + " voisins : " + sommetVoisin.get(i) + "\n";
        }
        return affichage;
    }
}
