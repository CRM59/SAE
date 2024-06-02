package Appli;

import fr.ulille.but.sae_s2_2024.*;

public class Sommet implements Lieu{
    String label = "";

    public Sommet(String label){
        this.label = label;
    }

    public String toString(){
        return this.label;
    }
}
