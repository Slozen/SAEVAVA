import lecture.LectureMembres;
import modele.MembreVille;

import java.io.File;
import java.io.IOException;

public class testLectureMembres {

    public static void main(String[] args) {
        try {
            File fichier = new File("Membres" + File.separator + "membres_APPLI.txt" );
            MembreVille membre = LectureMembres.lectureMembre(fichier);

            membre.afficheMembre();

            System.out.println("1 - Psykokwak habite à : " + membre.getVille("Psykokwak"));
            System.out.println("2 - Démanta habite à : " + membre.getVille("Démanta"));
            System.out.println("3 - Machoc habite à : " + membre.getVille("Machoc"));
            System.out.println("4 - Crocrodil habite à : " + membre.getVille("Crocrodil"));
            System.out.println("5 - Chapignon habite à : " + membre.getVille("Chapignon"));
            System.out.println("6 - Leuphorie habite à : " + membre.getVille("Leuphorie"));
            System.out.println("7 - Ramoloss habite à : " + membre.getVille("Ramoloss"));
            System.out.println("8 - Écrapince habite à : " + membre.getVille("Écrapince"));
            System.out.println("9 - Minidraco habite à : " + membre.getVille("Minidraco"));

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }
}

