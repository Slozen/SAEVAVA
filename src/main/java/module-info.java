module org.example.projetpokemon {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.example.projetpokemon to javafx.fxml;
    exports org.example.projetpokemon;
    exports vue;
}