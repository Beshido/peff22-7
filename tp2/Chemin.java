package com.company;

public class Chemin {
    private String villeA;
    private String villeB;
    private int distance;

    public Chemin(String villeA, String villeB, int distance) {
            this.villeA = villeA;
            this.villeB = villeB;
            this.distance = distance;
    }
    public String getVilleA() {
        return villeA;
    }
    public String getVilleB() {
        return villeB;
    }
    public int getDistance() {
        return distance;
    }
}