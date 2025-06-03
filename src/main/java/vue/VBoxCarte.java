package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import modele.CarteDistance;

public class VBoxCarte extends VBox {

    private static ToggleGroup toggleGroup;
    private static RadioButton btnTopologique;
    private static RadioButton btnHeuristique;
    private static VBox trajetBox;
    private static ScrollPane scrollPaneTrajet;
    private static Label distanceLabel;

    public VBoxCarte() {
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        this.getStyleClass().add("main-container");

        creerSection1(); // boutons
        creerSection2(); // affichage du trajet
        creerSection3(); // distance
    }

    private void creerSection1() {
        VBox section1 = new VBox(10);
        section1.setPadding(new Insets(10));
        section1.getStyleClass().add("section-container");

        Label titre = new Label("Choix de l'algorithme");
        titre.getStyleClass().add("section-title");

        toggleGroup = new ToggleGroup();

        btnTopologique = new RadioButton("Tri Topologique");
        btnTopologique.setToggleGroup(toggleGroup);

        btnHeuristique = new RadioButton("Parcours Heuristique");
        btnHeuristique.setToggleGroup(toggleGroup);
        btnTopologique.setSelected(true);

        btnTopologique.getStyleClass().add("algo-button");
        btnHeuristique.getStyleClass().add("algo-button");

        section1.getChildren().addAll(titre, btnTopologique, btnHeuristique);
        this.getChildren().add(section1);
    }

    private void creerSection2() {
        VBox section2 = new VBox(10);
        section2.setPadding(new Insets(10));
        section2.getStyleClass().add("section-container");

        Label titre = new Label("Trajet de Livraison");
        titre.getStyleClass().add("section-title");

        trajetBox = new VBox(8);
        trajetBox.setPadding(new Insets(10));
        trajetBox.getStyleClass().add("scenario-info-box");

        scrollPaneTrajet = new ScrollPane(trajetBox);
        scrollPaneTrajet.setFitToWidth(true);
        scrollPaneTrajet.setPrefHeight(250);
        scrollPaneTrajet.getStyleClass().add("scenario-scroll-pane");

        section2.getChildren().addAll(titre, scrollPaneTrajet);
        this.getChildren().add(section2);
    }

    private void creerSection3() {
        VBox section3 = new VBox();
        section3.setAlignment(Pos.CENTER);
        section3.setPadding(new Insets(15));
        section3.setSpacing(10);
        section3.getStyleClass().add("section-container");

        distanceLabel = new Label("Distance : - km");
        distanceLabel.getStyleClass().add("summary-label");

        section3.getChildren().add(distanceLabel);
        this.getChildren().add(section3);
    }

    public RadioButton getBtnTopologique() {
        return btnTopologique;
    }

    public RadioButton getBtnHeuristique() {
        return btnHeuristique;
    }

    public static VBox getTrajetBox() {
        return trajetBox;
    }

    public static Label getDistanceLabel() {
        return distanceLabel;
    }

    public static void afficherTrajet(String[] villes) {
        trajetBox.getChildren().clear();
        for (String ville : villes) {
            Label l = new Label("📍 " + ville);
            l.getStyleClass().add("route-label");
            trajetBox.getChildren().add(l);
        }
    }

    public static void majDistance(String d) {
        distanceLabel.setText("Distance : " + d + " km");
    }

    public String getAlgoSelectionne() {
        if (btnTopologique.isSelected())
            return "topologique";
        else return "heuristique";
    }

}
