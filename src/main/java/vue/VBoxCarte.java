package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import modele.Solution;

import java.util.ArrayList;

import static constante.ConstanteComboBoxKmeilleures.K_MEILLEURES;

public class VBoxCarte extends VBox {

    private ToggleGroup toggleGroup;
    private ComboBox<String> comboBoxKmeilleures;
    private RadioButton btnTopologique;
    private RadioButton btnHeuristique;
    private RadioButton btnKMeilleures;
    private Button btnCreerScenario;
    private Button btnModifierScenario;
    private VBox trajetBox;
    private ScrollPane scrollPaneTrajet;
    private Label distanceLabel;

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

        Label labelK = new Label("Choisir une solution :");
        comboBoxKmeilleures = new ComboBox<>();
        comboBoxKmeilleures.getItems().setAll(K_MEILLEURES);
        comboBoxKmeilleures.setValue(comboBoxKmeilleures.getItems().get(0));
        comboBoxKmeilleures.setDisable(true);
        labelK.getStyleClass().add("section-title");
        section1.getChildren().addAll(labelK, comboBoxKmeilleures);

        toggleGroup = new ToggleGroup();

        btnTopologique = new RadioButton("Tri Topologique");
        btnTopologique.setToggleGroup(toggleGroup);
        btnTopologique.setSelected(true);

        btnHeuristique = new RadioButton("Parcours Heuristique");
        btnHeuristique.setToggleGroup(toggleGroup);

        btnKMeilleures = new RadioButton("K meilleurs solutions");
        btnKMeilleures.setToggleGroup(toggleGroup);

        btnTopologique.getStyleClass().add("algo-button");
        btnHeuristique.getStyleClass().add("algo-button");

        section1.getChildren().addAll(titre, btnTopologique, btnHeuristique, btnKMeilleures);
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

        VBox boutonActionsBox = new VBox(10);
        boutonActionsBox.setPadding(new Insets(10));
        boutonActionsBox.setAlignment(Pos.CENTER);

        btnModifierScenario = new Button("Modif Scenario");
        btnModifierScenario.getStyleClass().add("action-button");

        btnCreerScenario = new Button("Créer Scenario");
        btnCreerScenario.getStyleClass().add("action-button");

        btnCreerScenario.setOnAction(event -> {
            Stage stage = (Stage) btnCreerScenario.getScene().getWindow();
            Scene nouvelleScene = VBoxCreationScenario.creerScene(stage);
            stage.setScene(nouvelleScene);
        });

        boutonActionsBox.getChildren().addAll(btnModifierScenario, btnCreerScenario);

        section2.getChildren().addAll(titre, scrollPaneTrajet, boutonActionsBox);
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

    public VBox getTrajetBox() {
        return trajetBox;
    }

    public Label getDistanceLabel() {
        return distanceLabel;
    }

    public void afficherTrajet(String[] villes) {
        trajetBox.getChildren().clear();
        for (String ville : villes) {
            Label l = new Label("📍 " + ville);
            l.getStyleClass().add("route-label");
            trajetBox.getChildren().add(l);
        }
    }

    public void afficherKTrajets(ArrayList<Solution> solutions) {
        trajetBox.getChildren().clear();

        int num = 1;
        for (Solution s : solutions) {
            Label titre = new Label("🔢 Solution " + num + " - " + s.getDistance() + " km");
            titre.getStyleClass().add("summary-label");
            trajetBox.getChildren().add(titre);

            for (String ville : s.getTrajet()) {
                Label l = new Label("📍 " + ville);
                l.getStyleClass().add("route-label");
                trajetBox.getChildren().add(l);
            }

            trajetBox.getChildren().add(new Separator());
            num++;
        }
    }

    public void majDistance(String d) {
        distanceLabel.setText("Distance : " + d + " km");
    }

    public String getAlgoSelectionne() {
        if (btnTopologique.isSelected()){
            comboBoxKmeilleures.setDisable(true);
            return "topologique";
        }
        if (btnHeuristique.isSelected()){
            comboBoxKmeilleures.setDisable(true);
            return "heuristique";
        }
        else{
            comboBoxKmeilleures.setDisable(false);
            return "k-meilleures";
        }
    }

    public int getNbKmeilleurs(){
        return Integer.parseInt(comboBoxKmeilleures.getValue());
    }

}
