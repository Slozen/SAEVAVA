import modele.CarteDistance;
import lecture.LectureDistances;

import java.io.File;
import java.io.IOException;

public class ClientLectureDistances {
    public static void main(String[] args) {
        try {
            File fichier = new File("Distances" + File.separator + "distances.txt" );
            CarteDistance carte = LectureDistances.lectureDistances(fichier);

            carte.afficheDistances();
            System.out.println("distance : " + carte.distanceVilles("Lille", "Perpignan"));

            carte.getDistances();

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
