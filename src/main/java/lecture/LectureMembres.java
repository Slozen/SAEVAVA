package lecture;

import modele.MembreVille;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LectureMembres {

public static MembreVille lectureMembre(File fichier) throws IOException{
    MembreVille membreVersHabitat = new MembreVille();

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