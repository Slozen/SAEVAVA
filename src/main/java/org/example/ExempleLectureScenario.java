package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ExempleLectureScenario {
    public static void lectureScenario(File fichier) throws IOException{
        Scanner scannerFile = new Scanner(fichier);
        Scanner scannerLine;

        while(scannerFile.hasNextLine()){
            String line = scannerFile.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            String vendeur = scannerLine.next();
            scannerLine.next(); // "->"
            String acheteur = scannerLine.next();
            System.out.println("vendeur : " + vendeur + ", acheteur : "+acheteur );
            scannerLine.close();

        }
        scannerFile.close();
    }
}
