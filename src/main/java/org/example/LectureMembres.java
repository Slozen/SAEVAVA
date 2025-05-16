package modele;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LectureMembres {

public static void lectureMembre(File fichier) throws IOException{
    Scanner scannerFile = new Scanner(fichier);
    Scanner scannerLine;

    while(scannerFile.hasNextLine()){
        String line = scannerFile.nextLine();
        scannerLine = new Scanner(line).useDelimiter(" ");
        String membre = scannerLine.next();
        String ville = scannerLine.next();
        System.out.println("Membre: " + membre + ", Ville : "+ville );
        scannerLine.close();

    }
    scannerFile.close();
    }
}