package lecture;
import modele.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LectureMembre {

public static Membre lectureMembre(File fichier) throws IOException{
    Membre membreVersHabitat = new Membre();

    Scanner scannerFile = new Scanner(fichier);
    Scanner scannerLine;

    while(scannerFile.hasNextLine()){
        String line = scannerFile.nextLine();
        scannerLine = new Scanner(line).useDelimiter(" ");
        String membre = scannerLine.next();
        String ville = scannerLine.next();

        membreVersHabitat.ajoutMembre(membre, ville);
        scannerLine.close();

    }
    scannerFile.close();
    return membreVersHabitat;
    }
}