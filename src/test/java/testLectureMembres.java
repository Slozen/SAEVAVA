

import modele.LectureMembres;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class testLectureMembres {

    public static void main(String[] args) {
        try {
            File fichier = new File("membres" + File.separator + "membres_APPLI.txt" );
            LectureMembres.lectureMembre(fichier);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }
}

