import lecture.LectureScenario;
import modele.Scenario;

import java.io.File;
import java.io.IOException;

public class ClientExempleLectureScenario {
    public static void main(String[] args) {
        try {
            File fichier = new File("Scenarios" + File.separator + "scenario_0.txt" );
            Scenario scenario = LectureScenario.lectureScenario(fichier);
            scenario.afficheVentes();

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }



        }
    }

