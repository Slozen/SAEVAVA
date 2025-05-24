package modeles;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LectureScenarios {
    public static Scenario lectureScenario(File fichier) throws IOException{
        Scenario scenario = new Scenario();
        Scanner scannerFile = new Scanner(fichier);
        Scanner scannerLine;

        while(scannerFile.hasNextLine()){
            String line = scannerFile.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            String vendeur = scannerLine.next();
            scannerLine.next(); // "->"
            String acheteur = scannerLine.next();
            scenario.addVente(new Ventes(vendeur, acheteur));
            scannerLine.close();

        }
        scannerFile.close();
        return scenario;
    }


}
