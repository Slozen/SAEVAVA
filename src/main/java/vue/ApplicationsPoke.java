package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

public class ApplicationsPoke extends Application {

    public void start(Stage stage) {

        HBoxRoot root  = new HBoxRoot();
        Scene scene = new Scene(root,1000,(500));

        File[] fichiersCss = new File("StyleCSS").listFiles();
        for (File fichier : fichiersCss) {
            scene.getStylesheets().add(fichier.toURI().toString());
        }
        
        stage.setScene(scene);
        stage.setTitle("PokemonInterface");
        stage.show();

    }

    public static void main (String [] args) {
        Application.launch(args);
    }
}
