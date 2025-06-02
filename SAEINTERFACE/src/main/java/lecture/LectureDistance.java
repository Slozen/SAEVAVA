package lecture;
import modele.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LectureDistance {

    public static CarteDistance lectureDistances(File fichier) throws IOException {
        HashMap <String, ArrayList<Integer>> distances = new HashMap<>();
        ArrayList<String> villes = new ArrayList<>();

        Scanner scannerFile = new Scanner(fichier);
        Scanner scannerLine;

        while(scannerFile.hasNextLine()){
            String line = scannerFile.nextLine();
            scannerLine = new Scanner(line).useDelimiter(" ");
            String ville = scannerLine.next();
            villes.add(ville);

            ArrayList<Integer> ligneDistance = new ArrayList<>();

            while(scannerLine.hasNextInt()){
                ligneDistance.add(scannerLine.nextInt());

            }

            distances.put(ville, ligneDistance);
            scannerLine.close();
        }
        scannerFile.close();
        return new CarteDistance(villes, distances);

    }
}
