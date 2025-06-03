package vue;

import constante.ConstanteScenario;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import lecture.LectureScenario;
import modele.Scenario;
import modele.Ventes;
import java.io.File;
import java.io.IOException;

public class VboxPokemon extends VBox implements ConstanteScenario {

    private MenuButton menuScenario;
    private ToggleGroup toggleGroup;
    private Label labelScenario;
    private VBox scenarioInfoBox;
    private ScrollPane scenarioScrollPane;
    private Button letsGoBoutton;
    private Scenario scenarioCourant;
    private String nomScenarioCourant;

    public VboxPokemon(){
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        this.getStyleClass().add("main-container");

        creationSection1();
        creationSection2();
        creationSection3();
    }

    public void creationSection1() {
        VBox section1 = new VBox();
        section1.setSpacing(10);
        section1.setPadding(new Insets(15));
        section1.getStyleClass().add("section-container");

        Label topTitle = new Label("Sélection du Scénario");
        topTitle.getStyleClass().add("section-title");

        menuScenario = new MenuButton("Choisir un scénario");
        menuScenario.getStyleClass().add("scenario-menu");
        toggleGroup = new ToggleGroup();

        for (String scenario : CONSTANTE_SCENARIO) {
            RadioMenuItem menuItem = new RadioMenuItem(scenario);
            menuItem.setUserData(scenario);
            menuItem.setToggleGroup(toggleGroup);

            // à mettre dans le controleur plus tard
            menuItem.setOnAction(event -> {
                if (menuItem.isSelected()) {
                    nomScenarioCourant = scenario;
                    labelScenario.setText("Scénario sélectionné: " + scenario);
                    menuScenario.setText(scenario);
                    chargerScenario(scenario);
                    letsGoBoutton.setDisable(false);
                }
            });

            menuScenario.getItems().add(menuItem);
        }

        labelScenario = new Label("Aucun scénario sélectionné");
        labelScenario.getStyleClass().add("selected-scenario-label");
        
        section1.getChildren().addAll(topTitle, menuScenario, labelScenario);
        this.getChildren().add(section1);
    }
    

    public void creationSection2() {

        VBox section2 = new VBox();
        section2.setSpacing(10);
        section2.setPadding(new Insets(15));
        section2.getStyleClass().add("section-container");

        Label middleTitle = new Label("Informations du Scénario");
        middleTitle.getStyleClass().add("section-title");

        scenarioInfoBox = new VBox();
        scenarioInfoBox.setSpacing(8);
        scenarioInfoBox.setPadding(new Insets(10));
        scenarioInfoBox.getStyleClass().add("scenario-info-box");

        scenarioScrollPane = new ScrollPane(scenarioInfoBox);
        scenarioScrollPane.setFitToWidth(true);
        scenarioScrollPane.setPrefHeight(250);
        scenarioScrollPane.getStyleClass().add("scenario-scroll-pane");
        
        section2.getChildren().addAll(middleTitle, scenarioScrollPane);
        this.getChildren().add(section2);

        majScenarioInfo("Sélectionnez un scénario pour voir les détails des transactions");
    }

    public void creationSection3() {

        VBox section3 = new VBox();
        section3.setSpacing(10);
        section3.setPadding(new Insets(15));
        section3.setAlignment(Pos.CENTER);
        section3.getStyleClass().add("section-container");

        Label bottomTitle = new Label("Démarrer la Livraison");
        bottomTitle.getStyleClass().add("section-title");

        letsGoBoutton = new Button("Let's Go!");
        letsGoBoutton.getStyleClass().add("lets-go-boutton");
        letsGoBoutton.setDisable(true);

        letsGoBoutton.setOnMouseEntered(e -> {
            if (!letsGoBoutton.isDisabled()) {
                letsGoBoutton.getStyleClass().add("lets-go-button");
            }
        });
        
        letsGoBoutton.setOnMouseExited(e -> {
            if (!letsGoBoutton.isDisabled()) {
                letsGoBoutton.getStyleClass().add("lets-go-boutton");
            }
        });

        section3.getChildren().addAll(bottomTitle, letsGoBoutton);
        this.getChildren().add(section3);
    }

    public void chargerScenario(String nomScenario) {
        try {

            String numScenario = nomScenario.substring(nomScenario.lastIndexOf(" ") + 1);
            String nomFichier = "scenario_" + numScenario + ".txt";
            File fichierScenario = new File("Scenarios/" + nomFichier);
            
            scenarioCourant = LectureScenario.lectureScenario(fichierScenario);
            affichageScenarioInfo(scenarioCourant);

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void affichageScenarioInfo(Scenario scenario) {
        scenarioInfoBox.getChildren().clear();

        Label headerLabel = new Label("📦 Livraisons à effectuer :");
        headerLabel.getStyleClass().add("header-label");
        scenarioInfoBox.getChildren().add(headerLabel);

        Separator separator = new Separator();
        scenarioInfoBox.getChildren().add(separator);

        int numLivraison = 1;
        for (Ventes vente : scenario.getVentes()) {
            VBox livraisonBox = new VBox();
            livraisonBox.setSpacing(5);
            livraisonBox.setPadding(new Insets(10));
            livraisonBox.getStyleClass().add("livraison-box");
            
            Label livraisonLabel = new Label("🚚 Livraison " + numLivraison);
            livraisonLabel.getStyleClass().add("livraison-label");
            
            Label vendeurLabel = new Label("👤 Vendeur: " + vente.getVendeur());
            vendeurLabel.getStyleClass().add("vendeur-label");
            
            Label acheteurLabel = new Label("🎯 Acheteur: " + vente.getAcheteur());
            acheteurLabel.getStyleClass().add(("acheteur-label"));
            
            Label routeLabel = new Label("📍 " + vente.getVendeur() + " → " + vente.getAcheteur());
            routeLabel.getStyleClass().add("route-label");
            
            livraisonBox.getChildren().addAll(livraisonLabel, vendeurLabel, acheteurLabel, routeLabel);
            scenarioInfoBox.getChildren().add(livraisonBox);
            
            numLivraison++;
        }

        Separator summarySeperator = new Separator();
        scenarioInfoBox.getChildren().add(summarySeperator);
        
        Label summaryLabel = new Label("📊 Total : " + scenario.getVentes().size() + " livraisons");
        summaryLabel.getStyleClass().add("summary-label");
        scenarioInfoBox.getChildren().add(summaryLabel);
    }

    public void majScenarioInfo(String message) {
        scenarioInfoBox.getChildren().clear();
        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().add("message-label");
        messageLabel.setWrapText(true);
        scenarioInfoBox.getChildren().add(messageLabel);
    }

    public Scenario getScenarioCourant() {
        return scenarioCourant;
    }

    public Button getLetsGoBoutton() {
        return letsGoBoutton;
    }



}
