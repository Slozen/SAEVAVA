package vue;

import constant.ConstanteScenario;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import lecture.LectureScenarios;
import modele.Scenario;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class VboxPokemon extends VBox implements ConstanteScenario {


    public VboxPokemon() throws IOException {

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(CONSTANTE_SCENARIO);
        comboBox.setPromptText("Choisir un Scenario");
        this.getChildren().add(comboBox);

        StackPane stackPaneScenario = new StackPane();

        for (int i = 0 ; i < 9 ; i++ ){
            String fileName = "Scenarios" + File.separator + "scenario_" + i + ".txt";
            File file = new File(fileName);
            Scenario scenario = LectureScenarios.lectureScenario(file);

            VBox Scenarioboite =  new VBox();
            ScrollPane scrollPaneScenario = new ScrollPane();
            scrollPaneScenario.setContent(Scenarioboite);
            scrollPaneScenario.setAccessibleText("pose");
            VBox.setMargin(scrollPaneScenario, new Insets(4));
            Label labelScenarioContenue = new Label(scenario.toString());
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
