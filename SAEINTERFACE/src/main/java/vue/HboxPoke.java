package vue;

import javafx.scene.layout.HBox;
import modele.Vente;

import java.io.IOException;

public class HboxPoke extends HBox {


    public HboxPoke() throws IOException {


        VboxPokemon vboxPokemon = new VboxPokemon();
        GrapheInterface grapheInterface = new GrapheInterface();
        this.getChildren().add(vboxPokemon);
        this.getChildren().add(grapheInterface);

    }





}
