package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxCreationScenario {

    public static Scene creerScene(Stage stagePrincipal) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label titre = new Label("Création d'un nouveau scénario");
        titre.getStyleClass().add("section-title");

        TextField vendeurField = new TextField();
        vendeurField.setPromptText("Ville du vendeur");

        TextField acheteurField = new TextField();
        acheteurField.setPromptText("Ville de l'acheteur");

        Button boutonRetour = new Button("Retour");
        boutonRetour.setOnAction(e -> stagePrincipal.setScene(HBoxRoot.getMainScene())); // revenir

        layout.getChildren().addAll(titre, vendeurField, acheteurField, boutonRetour);
        return new Scene(layout, 1000, 600);
    }


}
