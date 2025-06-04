package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import lecture.LectureDistances;
import lecture.LectureMembres;
import modele.*;
import vue.HBoxRoot;
import vue.VBoxCarte;
import vue.VboxPokemon;

import java.io.File;
import java.util.ArrayList;

public class Controleur implements EventHandler {
    @Override
    public void handle(Event event) {
        VboxPokemon livraison = HBoxRoot.getRightSide();
        VBoxCarte carte = HBoxRoot.getLeftSide();
        CarteDistance carteDistance = HBoxRoot.getCarteDistance();

        if (event.getSource() instanceof Button){
            try {

                Scenario scenario = livraison.getScenarioCourant();
                File fichierMembres = new File("Membres" + File.separator + "membres_APPLI.txt");
                MembreVille membres = LectureMembres.lectureMembre(fichierMembres);

                ArrayList<Livraison> livraisons = ConversionVentesLivraisons.conversionVentesLivraisons(scenario, membres );
                Graphe graphe = new Graphe(livraisons);
                ArrayList<String> trajet = null;

                String algo = carte.getAlgoSelectionne();
                if (algo.equals("topologique")) {
                    trajet = graphe.triTopologique();
                    Kilometrage kilometre = new Kilometrage(carteDistance);
                    int distance = kilometre.calculerDistanceTotale(trajet);

                    carte.afficherTrajet(trajet.toArray(new String[0]));
                    carte.majDistance(String.valueOf(distance));
                }
                if (algo.equals("k-meilleures")){
                    int k = carte.getNbKmeilleurs();
                    KMeilleuresSolutions algoK = new KMeilleuresSolutions(graphe,carteDistance,k);
                    ArrayList<Solution> solutions = algoK.calculerMeilleuresSolutions();
                    carte.afficherKTrajets(solutions);
                    carte.majDistance(" - ");

                }
                if(algo.equals("heuristique")) {
                    trajet = graphe.parcoursHeuristique(carteDistance);
                    Kilometrage kilometre = new Kilometrage(carteDistance);
                    int distance = kilometre.calculerDistanceTotale(trajet);

                    carte.afficherTrajet(trajet.toArray(new String[0]));
                    carte.majDistance(String.valueOf(distance));
                }



            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }



        }
    }

}