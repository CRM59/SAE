package Appli;


import fr.ulille.but.sae_s2_2024.*;

public class Arrete implements Trancon {
    Lieu depart;
    Lieu arrivee;
    ModaliteTransport transport;

    public Arrete(Lieu depart, Lieu arrivee, ModaliteTransport transport){
        this.depart = depart;
        this.arrivee = arrivee;
        this.transport = transport;
    }

    public Lieu getArrivee(){
        return this.arrivee;
    }

    public Lieu getDepart(){
        return this.depart;
    }

    public ModaliteTransport getModalite(){
        return this.transport;
    }


    public String toString(){
        return this.depart + " à " + this.arrivee;
    }

}
