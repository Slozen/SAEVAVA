package modele;

import java.util.List;

public class Solution {
    private final List<String> trajet;
    private final int distance;

    public Solution(List<String> trajet, int distance) {
        this.trajet = trajet;
        this.distance = distance;
    }

    public List<String> getTrajet() {
        return trajet;
    }

    public int getDistance() {
        return distance;
    }

    public String toString() {
        return "Distance : " + distance + " km, Trajet : " + trajet;
    }
}
