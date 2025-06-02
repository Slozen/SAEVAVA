package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ApplicationsPoke extends Application {

    public void start(Stage stage) throws IOException {

        HBox root =  new HboxPoke();
        //VBox root  = new VboxPokemon();
        Scene scene = new Scene(root,900,(500));
        stage.setScene(scene);
        stage.setTitle("PokemonInterface");
        stage.show();


    }

    public static void main (String [] args) {
        Application.launch(args);
    }
}
