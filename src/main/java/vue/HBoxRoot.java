package vue;

import controleur.Controleur;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import lecture.LectureDistances;
import modele.CarteDistance;

import java.io.File;

public class HBoxRoot extends HBox {
    private static VboxPokemon rightSide; // Conteneur pour le côté droit
    private static VBoxCarte leftSide;
    private static CarteDistance carteDistance;
    private static Controleur controleur;
    private static Scene mainScene;


    public HBoxRoot() {
        leftSide = new VBoxCarte();
        rightSide = new VboxPokemon();
        try {
            carteDistance = LectureDistances.lectureDistances(new File("Distances" + File.separator + "distances.txt"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        controleur = new Controleur();
        rightSide.getLetsGoBoutton().setOnAction(controleur);


        // Ajout des conteneurs à HBXRoot
        this.getChildren().addAll(leftSide, rightSide);

        // Configuration des proportions de largeur
        leftSide.setPrefWidth(700); // Largeur du côté gauche
        rightSide.setPrefWidth(300);

    }

    public static VboxPokemon getRightSide() {
        return rightSide;
    }

    public static VBoxCarte getLeftSide() {
        return leftSide;
    }

    public static CarteDistance getCarteDistance() {
        return carteDistance;
    }

    public static Controleur getControleur() {
        return controleur;
    }

    public static void setMainScene(Scene scene) {
        mainScene = scene;
    }
    public static Scene getMainScene() {
        return mainScene;
    }
}
