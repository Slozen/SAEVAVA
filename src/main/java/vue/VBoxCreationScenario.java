package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lecture.LectureMembres;
import modele.CreationScenario;
import modele.MembreVille;
import modele.Scenario;
import modele.Ventes;

import java.io.File;

public class VBoxCreationScenario {

    public static Scene creerScene(Stage stagePrincipal) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f4f4f4;");

        Label titre = new Label("Création d’un Scénario");
        titre.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        ComboBox<String> cbVendeur = new ComboBox<>();
        ComboBox<String> cbAcheteur = new ComboBox<>();
        cbVendeur.setPromptText("Choisir un vendeur");
        cbAcheteur.setPromptText("Choisir un acheteur");

        Scenario scenarioTemp = new Scenario();
        ListView<String> listeVentes = new ListView<>();

        try {
            File fichier = new File("Membres/membres_APPLI.txt");
            MembreVille membres = LectureMembres.lectureMembre(fichier);

            cbVendeur.getItems().addAll(membres.getMap().keySet());
            cbAcheteur.getItems().addAll(membres.getMap().keySet());
        } catch (Exception e) {
            System.out.println("Erreur lecture membres : " + e.getMessage());
        }

        Button btnAjouter = new Button("Ajouter Vente");
        btnAjouter.setOnAction(e -> {
            String vendeur = cbVendeur.getValue();
            String acheteur = cbAcheteur.getValue();

            if (vendeur != null && acheteur != null && !vendeur.equals(acheteur)) {
                Ventes vente = new Ventes(vendeur, acheteur);
                scenarioTemp.addVente(vente);
                listeVentes.getItems().add(vendeur + " -> " + acheteur);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Vendeur et acheteur doivent être différents.");
                alert.showAndWait();
            }
        });

        Button btnRetour = new Button("Retour");
        btnRetour.setOnAction(e -> {
            stagePrincipal.setScene(HBoxRoot.getMainScene());
        });

        VBox boxForm = new VBox(10, new Label("Vendeur:"), cbVendeur, new Label("Acheteur:"), cbAcheteur, btnAjouter);
        boxForm.setPadding(new Insets(10));
        boxForm.getStyleClass().add("vendeur-acheteur");

        VBox boxListe = new VBox(10, new Label("Ventes ajoutées:"), listeVentes);
        boxListe.setPadding(new Insets(10));
        boxListe.getStyleClass().add("ventes");

        HBox boutonRetourBox = new HBox(btnRetour);
        boutonRetourBox.setPadding(new Insets(10));
        boutonRetourBox.setAlignment(javafx.geometry.Pos.CENTER);

        // Champ pour nom du fichier
        TextField nomFichierField = new TextField();
        nomFichierField.setPromptText("Nom du fichier sans extension (.txt)");

        Button btnEnregistrer = new Button("Enregistrer le scénario");

        btnEnregistrer.setOnAction(e -> {
            String nomFichier = nomFichierField.getText().trim();

            if (nomFichier.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez entrer un nom de fichier.");
                alert.showAndWait();
            }
            else {
                try {
                    File fichier = new File("Scenarios" + File.separator + nomFichier + ".txt");
                    CreationScenario.enregistrerScenarioDansFichier(scenarioTemp, fichier);

                    nomFichierField.clear();
                    listeVentes.getItems().clear();
                    scenarioTemp.getVentes().clear();
                }
                catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur : " + ex.getMessage());
                    alert.showAndWait();
                }
            }
        });

        HBox boutonEnregistrerBox = new HBox(10, new Label("Nom du fichier :"), nomFichierField,btnEnregistrer);
        boutonRetourBox.setPadding(new Insets(10));
        boutonRetourBox.setAlignment(javafx.geometry.Pos.CENTER);


        root.getChildren().addAll(titre, boxForm, boxListe, boutonEnregistrerBox ,boutonRetourBox);
        HBoxRoot.getRightSide().majMenuScenario();


        return new Scene(root, 500, 600);
    }


}
