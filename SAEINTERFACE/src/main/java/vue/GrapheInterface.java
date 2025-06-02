package vue;
import constant.ConstanteScenario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lecture.LectureMembre;
import lecture.LectureScenario;
import modele.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrapheInterface extends VBox implements ConstanteScenario {



    public GrapheInterface() throws IOException {

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(CONSTANTE_SCENARIO);
        comboBox.setPromptText("Choisir un Scenario");
        this.getChildren().add(comboBox);

        StackPane stackPaneScenario = new StackPane();

        for (int i = 0 ; i < 9 ; i++ ){
            String fileName = "Scenarios" + File.separator + "scenario_" + i + ".txt";
            File fichierMembres = new File("Membres" + File.separator + "membres_APPLI.txt");
            File file = new File(fileName);
            Scenario scenario = LectureScenario.lectureScenario(file);
            Membre membres = LectureMembre.lectureMembre(fichierMembres);
            ArrayList<Livraison> livraisons = ConversionVentesLivraisons.conversionVentesLivraisons(scenario, membres);
            Graphe graphepoke = new Graphe(livraisons);

            VBox Scenarioboite =  new VBox();
            ScrollPane scrollPaneScenario = new ScrollPane();
            scrollPaneScenario.setContent(Scenarioboite);
            scrollPaneScenario.setAccessibleText("pose");
            VBox.setMargin(scrollPaneScenario, new Insets(4));
            Label labelScenarioContenue = new Label(graphepoke.toString());
            Label labelTitre = new Label(CONSTANTE_SCENARIO[i]);
            VBox.setMargin(labelScenarioContenue, new Insets(8));
            Scenarioboite.getChildren().add(labelTitre);
            Scenarioboite.getChildren().add(labelScenarioContenue);


            stackPaneScenario.getChildren().add(scrollPaneScenario);
        }
        this.getChildren().add(stackPaneScenario);


        List<Node> listScrollPanes = stackPaneScenario.getChildren();
        Button boutonNext = new Button(">");
        Button boutonPrevious = new Button("<");
        this.getChildren().addAll(boutonNext,boutonPrevious);


        boutonNext.setOnAction(event -> {

            listScrollPanes.get(0).toFront();
        });

        boutonPrevious.setOnAction(event -> {

            listScrollPanes.get(8).toBack();
        });
    }

}
