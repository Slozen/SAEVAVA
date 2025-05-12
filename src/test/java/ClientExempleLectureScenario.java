import org.example.ExempleLectureScenario;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ClientExempleLectureScenario {
    public static void main(String[] args) {
        try {
            File fichier = new File("Scenario" + File.separator + "scenario_0.txt" );
            ExempleLectureScenario.lectureScenario(fichier);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }
}
