package Appli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.ulille.but.sae_s2_2024.Chemin;

public class Voyageur {

    public static void main(String[] args) throws IndexOutOfBoundsException, NullValueException, NullPointerException, FileNotFoundException, IOException {
        
        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> changementsModalites = new ArrayList<String>();
        
        if(args[0] != null){
            data = donneesCSV.recupererDonnees(args[0]);

            //     "Nongaillard;A;Train;0;0;0",
            // "A;E;Train;1;0.2;10",
            // "E;Y;Train;6;0.7;5",
            // "Y;Z;Train;1;0.9;3",
            // "A;R;Train;3;0.8;15",
            // "R;Z;Train;2;0.2;7",
            // "A;T;Train;5;0.5;20",
            // "T;Z;Train;4;0.1;1",
            // "Z;IUT;Train;0;0;0"
            
            
            // "A;B;Train;60;1.7;80",
            // "B;C;Train;60;1.7;80"
        }else{
        
        data = donneesCSV.recupererDonnees("data.csv");
        }


        if(args[1] != null){
            changementsModalites = donneesCSV.recupererDonnees(args[1]);
        }else{
            changementsModalites = donneesCSV.recupererDonnees("correspondances.csv");
        }


        // String[] changementsModalites = new String[0];


        Plateforme voyageur;
        
        PlusCourtsChemins creationsChemins = new PlusCourtsChemins();
        
        try{
            Plateforme.dataValide(data);
            

            String nomUtilisateur = Keyboard.readString("Quel est votre nom d'utilisateur ? ");
            
            //String nomUtilisateur = "Léo";

                System.out.println("Bienvenue " + nomUtilisateur + " ! ");
                
                
                //Entrées des voyageurs sur leur critères de voyage
                
                boolean valide = false;
                //ModaliteTransport transport = ModaliteTransport.TRAIN;
                TypeCout tri = TypeCout.PRIX;
                int k = 100;
           
                // while (!valide) {
                //     try{
                //         transport = Plateforme.choixModalite();
                //         valide = true;
                //     }
                //     catch(KeyboardException e){
                //         valide = false;
                //         System.err.println("Mauvaise valeur entrée: " + e);
                //     }
                // }

                //valide = false;


                //choix du tri

                while (!valide) {
                    try{
                        tri = Plateforme.choixTriType();
                        valide = true;
                    }
                    catch(KeyboardException ke){
                        valide = false;
                        System.err.println("Mauvaise valeur entrée: " + ke);
                    }
                }

                valide = false;

                //choix du nombre de plus courts chemins

                while (!valide) {
                    try{
                        k = Plateforme.choixNombrePlusCourtsChemins();
                        valide = true;
                    }
                    catch(KeyboardException ke){
                        valide = false;
                        System.err.println("Mauvaise valeur entrée: " + ke);
                    }
                }


                valide = false;
                int[] bornesPrix = new int[]{0,180000};
                int[] bornesTemps = new int[]{0,180000};
                int[] bornesCo2 = new int[]{0,180000};

                //choix du nombre des bornes de prix

                while (!valide) {
                    try{
                        bornesPrix = Plateforme.choixBornesPrix();
                        valide = true;
                    }
                    catch(KeyboardException ke){
                        valide = false;
                        System.err.println("Mauvaise valeur entrée: " + ke);
                    }
                    catch(NumberFormatException nfe){
                        System.err.println(nfe);
                    }
                }

                valide = false;

                //choix du nombre des bornes de temps

                while (!valide) {
                    try{
                        bornesTemps = Plateforme.choixBornesTemps();
                        valide = true;
                    }
                    catch(KeyboardException ke){
                        valide = false;
                        System.err.println("Mauvaise valeur entrée: " + ke);
                    }
                    catch(NumberFormatException nfe){
                        System.err.println(nfe);
                    }
                }

                valide = false;

                //choix du nombre des bornes de co2

                while (!valide) {
                    try{
                        bornesCo2 = Plateforme.choixBornesCO2();
                        valide = true;
                    }
                    catch(KeyboardException ke){
                        valide = false;
                        System.err.println("Mauvaise valeur entrée: " + ke);
                    }
                    catch(NumberFormatException nfe){
                        System.err.println(nfe);
                    }
                }
                
                

                // ModaliteTransport transport = ModaliteTransport.TRAIN;
                // TypeCout tri = TypeCout.PRIX;
                // int k = 100;

                Sommet origine =  new Sommet("sommet");
                Sommet puit =  new Sommet("sommet");

                valide = false;

                while (!valide) {
                    try{
                        origine = Plateforme.choixOrigine(data);
                        valide = true;
                    }
                    catch(IncorrectSommetException ise){
                        valide = false;
                        System.err.println("Mauvaise valeur entrée: " + ise);
                    }
                }


                valide = false;

                while (!valide) {
                    try{
                        puit = Plateforme.choixPuit(data, origine);
                        valide = true;
                    }
                    catch(IncorrectSommetException ise){
                        valide = false;
                        System.err.println("Mauvaise valeur entrée: " + ise);
                    }
                }

                
                // int[] bornesPrix = new int[]{0,180000};
                // int[] bornesTemps = new int[]{0,180000};
                // int[] bornesCo2 = new int[]{0,180000};
                
                
                //Créations des chemins les plus courts pour chaque type de tri

                List<Chemin> cheminsPrix = new ArrayList<Chemin>();
                List<Chemin> cheminsTemps = new ArrayList<Chemin>();
                List<Chemin> cheminsCO2 = new ArrayList<Chemin>();
                
                try{
                    cheminsPrix = creationsChemins.creationCheminsPlusCourts(data, changementsModalites, TypeCout.PRIX, origine, puit);
                    cheminsTemps = creationsChemins.creationCheminsPlusCourts(data, changementsModalites, TypeCout.TEMPS, origine, puit);
                    cheminsCO2 = creationsChemins.creationCheminsPlusCourts(data, changementsModalites, TypeCout.CO2, origine, puit);
                }
                catch(NullPointerException npe){
                    new NullPointerException();
                }


                
                System.out.println("Voici les meilleurs chemins disponibles pour vous " + nomUtilisateur + " :");

                ArrayList<String> cheminsFinaux = new ArrayList<String>();
        
    
                voyageur = new Plateforme(tri, k);
    
                if(tri.equals(TypeCout.PRIX)){
                    cheminsFinaux = voyageur.afficherChemins(cheminsPrix, cheminsTemps, cheminsCO2, bornesPrix, bornesTemps, bornesCo2);
                }
                else if(tri.equals(TypeCout.TEMPS)){
                    cheminsFinaux = voyageur.afficherChemins(cheminsTemps, cheminsPrix, cheminsCO2, bornesTemps, bornesPrix, bornesCo2);
                }
                else{
                    cheminsFinaux = voyageur.afficherChemins(cheminsCO2, cheminsTemps, cheminsPrix, bornesCo2, bornesTemps, bornesPrix);
                }


                for(int i = 0; i < cheminsFinaux.size(); i++){
                    System.out.println(cheminsFinaux.get(i));
                }
        }
        catch(IncorrectDataException e){
            System.err.println(e);
        }
        catch(IndexOutOfBoundsException e){
            System.err.println(e);
        }
        catch(FileNotFoundException fnfe){
            System.err.println(fnfe);
        }
        catch(IOException ioe){
            System.err.println(ioe);
        }
    }
}
