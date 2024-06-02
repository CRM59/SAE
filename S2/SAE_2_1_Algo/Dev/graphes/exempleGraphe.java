import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Appli.NullValueException;
import Appli.Plateforme;
import Appli.PlusCourtsChemins;
import Appli.Sommet;
import Appli.TypeCout;
import Appli.donneesCSV;
import fr.ulille.but.sae_s2_2024.Chemin;

public class exempleGraphe {

    public static void main(String[] args) throws IndexOutOfBoundsException, NullValueException, FileNotFoundException, IOException {
        
        
        ArrayList<String> data = donneesCSV.recupererDonnees("graphe.csv");


        ArrayList<String> changementsModalites = new ArrayList<String>();
        
        Plateforme voyageur;
        
        PlusCourtsChemins creationsChemins = new PlusCourtsChemins();
        
            TypeCout tri = TypeCout.TEMPS;
            int k = 3;
            
            int[] bornesPrix =  new int[]{0,7};
            int[] bornesTemps = new int[]{0,111111};
            int[] bornesCo2 = new int[]{0,111111};


            Sommet origine = new Sommet("Nongaillard");
            Sommet puit = new Sommet("IUT");

                
                
            List<Chemin> cheminsPrix = creationsChemins.creationCheminsPlusCourts(data, changementsModalites, TypeCout.PRIX, origine, puit);
            List<Chemin> cheminsTemps = creationsChemins.creationCheminsPlusCourts(data, changementsModalites, TypeCout.TEMPS, origine, puit);
            List<Chemin> cheminsCO2 = creationsChemins.creationCheminsPlusCourts(data, changementsModalites, TypeCout.CO2, origine, puit);
                
                
             voyageur = new Plateforme(tri, k);

            ArrayList<String> cheminsFinaux = new ArrayList<String>();

            cheminsFinaux = voyageur.afficherChemins(cheminsTemps, cheminsPrix, cheminsCO2, bornesTemps, bornesPrix, bornesCo2);
        

            for(int i = 0; i < cheminsFinaux.size(); i++){
                System.out.println(cheminsFinaux.get(i));
            }
        
    }
}
